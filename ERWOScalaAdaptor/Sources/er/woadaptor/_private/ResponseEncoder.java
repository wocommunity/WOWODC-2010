package er.woadaptor._private;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import org.apache.mina.core.buffer.IoBuffer;

import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;

public class ResponseEncoder implements MessageEncoder {

    private static final Set TYPES;

    static {
        Set types = new HashSet();
        types.add(ResponseWrapper.class);
        TYPES = Collections.unmodifiableSet(types);
    }

    private static final byte[] CRLF = new byte[] { 0x0D, 0x0A };

    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        WOResponse msg = (WOResponse) ((ResponseWrapper) message).response();
        IoBuffer buf = IoBuffer.allocate(256);
        // Enable auto-expand for easier encoding
        buf.setAutoExpand(true);

        try {
            // output all headers except the content length
            CharsetEncoder encoder = Charset.defaultCharset().newEncoder();
            buf.putString(msg.httpVersion(), encoder);
            buf.putString(" ", encoder);
            buf.putString(String.valueOf(msg.status()), encoder);
            switch (msg.status()) {
            case WOResponse.HTTP_STATUS_OK:
                buf.putString(" OK", encoder);
                break;
            case WOResponse.HTTP_STATUS_NOT_FOUND:
                buf.putString(" Not Found", encoder);
                break;
            default:
                buf.putString(" Whatever", encoder);
            }
            buf.put(CRLF);
            if (msg.headers() != null) {
                for (Iterator<Entry<String, NSArray<String>>> it = msg.headers().entrySet().iterator(); it.hasNext();) {
                    Entry<String, NSArray<String>> entry = it.next();
                    for (Object item : entry.getValue()) {
                        buf.putString(entry.getKey().toString(), encoder);
                        buf.putString(": ", encoder);
                        buf.putString(item.toString(), encoder);
                        buf.put(CRLF);
                    }
                }
            } else {
                buf.put(CRLF);
            }
            // now the content length is the body length
            buf.put(CRLF);
            // add body
            // FIXME: should loop, support contentStream
            buf.put(msg.content()._bytesNoCopy());
        } catch (CharacterCodingException ex) {
            ex.printStackTrace();
        }

        buf.flip();
        out.write(buf);
    }

    public Set getMessageTypes() {
        return TYPES;
    }
}
