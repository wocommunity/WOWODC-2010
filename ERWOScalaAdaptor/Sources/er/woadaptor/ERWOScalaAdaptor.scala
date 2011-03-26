package er.woadaptor

import java.io.IOException
import java.net.InetSocketAddress

import java.util.concurrent.Executors

import org.apache.log4j.Logger
import org.apache.mina.core.session.{IdleStatus, IoSession}
import org.apache.mina.transport.socket.nio.{NioSocketAcceptor, NioSession}
import org.apache.mina.filter.logging.LoggingFilter
import org.apache.mina.filter.codec.ProtocolCodecFilter
import org.apache.mina.core.service.{IoHandler, IoHandlerAdapter}
import org.apache.mina.core.session.IdleStatus
import org.apache.mina.core.filterchain.IoFilter

import com.webobjects.appserver.{WOAdaptor, WOApplication, WORequest, WOResponse}
import com.webobjects.appserver._private.WOProperties
import com.webobjects.foundation.{NSDelayedCallbackCenter, NSDictionary}

import er.woadaptor._private.{CodecFactory, RequestDecoder, ResponseEncoder, ResponseWrapper}

/**
* Apache-mina based WOAdaptor, currently experimental. 
* <ul>
* <li> Allows us to ditch
* WOWorkerThread, WOHttpIO, WOLowercaseCharArray, WODefaultAdaptor and a bunch
* of other classes.
* <li> Has an extensible architecture, so SSL, throttling or AJP should be easily plug-able.
* <li> uses NIO, which supposedly should give better performance.
* </ul>
* TODO: streaming, encoding, SSL, params (idle time etc), content-length, larger POST args.
* @author ak
* @author ravim (Scala port + Actor version)
* 
*/
class ERWOScalaAdaptor(name: String, config: NSDictionary[String, AnyRef]) extends WOAdaptor(name: String, config: NSDictionary[String, AnyRef]) {

	private val log = Logger.getLogger("er.woadaptor.ERWOScalaAdaptor")

	private val _host = config.objectForKey(WOProperties._HostKey).asInstanceOf[String]

	private var acceptor: NioSocketAcceptor = null

	// constructor logic
	private val portNumber = config.objectForKey(WOProperties._PortKey).asInstanceOf[Number]
	if (portNumber != null)
	_port = portNumber.intValue
	if (_port < 0)  _port = 0
	WOApplication.application.setPort(_port)
	WOApplication.application._setHost(_host)

	override def registerForEvents {
	    try {
	        acceptor = new NioSocketAcceptor()
	        acceptor.getFilterChain.addLast("logger", ERWOScalaAdaptor.logger)
	        acceptor.getFilterChain.addLast("protocolFilter", ERWOScalaAdaptor.protocolFilter);
	        acceptor.setHandler(new ERWOScalaAdaptor.Handler())
	        acceptor.getSessionConfig.setIdleTime(IdleStatus.BOTH_IDLE, 60)
	        acceptor.bind(new InetSocketAddress(_host, _port))

	        log.info("Started adaptor")
	    } catch {
	       case ex: IOException => 
	           log.error(ex, ex)
	    }
	}

	override def unregisterForEvents {
		if (acceptor != null) {
			acceptor.unbind
			acceptor = null
		}
	}

	override def dispatchesRequestsConcurrently = true

	override def port = _port
}

object ERWOScalaAdaptor {
	private lazy val _lastDitchErrorResponse: WOResponse = {
		val _lastDitchErrorResponse = new WOResponse()
		_lastDitchErrorResponse.setStatus(500)
		_lastDitchErrorResponse.setContent("An error occured")
		_lastDitchErrorResponse.setHeaders(NSDictionary.EmptyDictionary.asInstanceOf[java.util.Map[String, java.util.List[String]]])
		_lastDitchErrorResponse
	}
	protected lazy val codecFactory = new CodecFactory()
	protected lazy val protocolFilter = new ProtocolCodecFilter(codecFactory).asInstanceOf[IoFilter]
	protected lazy val logger = new LoggingFilter()

	protected class Handler extends IoHandlerAdapter {
		import scala.actors.Reactor
		import scala.actors.Actor._

		private case class PROCESS(session: IoSession, request: WORequest)

		/**
		 * WOWorker as an actor
		*/
		private lazy val woworker = new Reactor[Any] {
			def act() {
				loop {
					react {
					  case PROCESS(session: IoSession, request: WORequest) => {
							var woresponse = ERWOScalaAdaptor._lastDitchErrorResponse
							try {
								var process = (request != null)
								process &= !(!WOApplication.application.isDirectConnectEnabled && !request.isUsingWebServer)
								process &= !"womp".equals(request.requestHandlerKey)

								if (process) {
									woresponse = WOApplication.application.dispatchRequest(request)
									NSDelayedCallbackCenter.defaultCenter.eventEnded
								}
							} catch {
								case e: Exception => log.error("Exception processing request", e)
							}

							if (woresponse != null) {
								session.write(new ResponseWrapper(woresponse))
							}
						  }
					}
				}
			}
		}
		woworker.start
	
		private val log = Logger.getLogger("er.woadaptor.ERWOScalaAdaptor.Handler")
	
		override def sessionClosed(session: IoSession) {
			log.info("closed")
		}
	
		override def messageReceived(session: IoSession, message: AnyRef) {
			woworker ! PROCESS(session, message.asInstanceOf[WORequest])
		}
	
		override def exceptionCaught(session: IoSession, cause: Throwable) {
			log.info("exceptionCaught: " + cause, cause)
		}
	}
}
