package scala.womovies.components

import com.webobjects.appserver.{WOApplication, WOContext, WOComponent}

import scala.reflect.BeanProperty

import org.squeryl.adapters.H2Adapter
import org.squeryl.{Query, Session, SessionFactory}
import org.squeryl.PrimitiveTypeMode._

import scala.womovies.model._

import scala.collection.mutable.ListBuffer
import scala.collection.JavaConversions._

class InspectStudio(context: WOContext) extends WOComponent(context: WOContext) {
	// constructor logic
	Class.forName("org.h2.Driver")
	private val dbPath = WOApplication.application.resourceManager.pathForResourceNamed("Movies.h2.db", null, null)
	private val db = dbPath.substring(0, dbPath indexOf ".h2.db")
	SessionFactory.concreteFactory = Some(() => Session.create( 
			java.sql.DriverManager.getConnection("jdbc:h2:file:" + db, "", ""),
			new H2Adapter
	))
	
	@BeanProperty var studio: Studio = null
	@BeanProperty var movie: Movie = null
	
	// accessors
	lazy val movies: java.util.List[Movie] = {
		transaction {
			//Session.currentSession.setLogger(println(_))		// turn on SQL logging
			studio.movies.toList
		}	
	}
}