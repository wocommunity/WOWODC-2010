package er.woadaptor._private;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

import com.webobjects.appserver.WOApplication;
import com.webobjects.appserver.WORequest;
import com.webobjects.foundation.NSData;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;

import org.apache.mina.core.buffer.IoBuffer;

public class RequestDecoder implements MessageDecoder {

    private static final byte[] CONTENT_LENGTH = new String("Content-Length:").getBytes();

    private CharsetDecoder decoder = Charset.defaultCharset().newDecoder();

    public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
        try {
            return headerComplete(in) ? MessageDecoderResult.OK : MessageDecoderResult.NEED_DATA;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return MessageDecoderResult.NOT_OK;
    }

    public MessageDecoderResult decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        // Try to decode body
        WORequest request = parseRequest(new StringReader(in.getString(decoder)));

        if (request == null) {
            return MessageDecoderResult.NEED_DATA;
        }
        out.write(request);

        return MessageDecoderResult.OK;
    }

    private boolean headerComplete(IoBuffer in) throws Exception {

        int last = in.remaining() - 1;
        if (in.remaining() < 4)
            return false;

        int eoh = -1;
        for (int i = 0; i < last - 2; i++) {
            if (in.get(i) == (byte) 0x0D && in.get(i + 1) == (byte) 0x0A && in.get(i + 2) == (byte) 0x0D && in.get(i + 3) == (byte) 0x0A) {
                eoh = i + 3;
                break;
            }
        }
        if (eoh == -1)
            return false;
        byte[] bytes = new byte[eoh - 3];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = in.get(i);
        }
        String headers = new String(bytes);
        for (int i = 0; i < last; i++) {
            boolean found = false;
            for (int j = 0; j < CONTENT_LENGTH.length; j++) {
                if (in.get(i + j) != CONTENT_LENGTH[j]) {
                    found = false;
                    break;
                }
                found = true;
            }
            if (found) {
                // retrieve value from this position till next 0x0D 0x0A
                StringBuilder contentLength = new StringBuilder();
                for (int j = i + CONTENT_LENGTH.length; j < last; j++) {
                    if (in.get(j) == 0x0D)
                        break;
                    contentLength.append(new String(new byte[] { in.get(j) }));
                }
                // if content-length worth of data has been received then
                // the message is complete
                return (Integer.parseInt(contentLength.toString().trim()) + eoh == in.remaining());
            } else {
                // this may be wrong...
                return true;
            }
        }

        // the message is not complete and we need more data
        return false;
    }

    private WORequest parseRequest(Reader is) throws IOException {
        BufferedReader rdr = new BufferedReader(is);
        String line = rdr.readLine();
        String[] parts = line.trim().split(" ", 3);
        String url = parts[1];
        String method = parts[0];
        String version = parts[2];
        NSMutableDictionary<String, NSMutableArray<String>> headers = new NSMutableDictionary<String, NSMutableArray<String>>() {
            @Override
            public NSMutableArray<String> objectForKey(Object key) {
                return super.objectForKey(key.toString().toLowerCase());
            }
        };
        NSData content = null;

        // Read header
        while ((line = rdr.readLine()) != null && line.length() > 0) {
            String[] tokens = line.split(":\\s*", 2);
            String key = tokens[0];
            String value = tokens[1];
            NSMutableArray<String> items = headers.objectForKey(key);
            if (items == null) {
                items = new NSMutableArray<String>();
                headers.setObjectForKey(items, key.toLowerCase());
            }
            items.addObject(value);
        }

        if (headers.objectForKey("Content-Length") != null) {
            int len = Integer.parseInt(headers.objectForKey("Content-Length").lastObject());
            char[] buf = new char[len];
            if (rdr.read(buf) == len) {
                line = String.copyValueOf(buf);
            }
            content = new NSData(line);
        }
        WORequest request = WOApplication.application().createRequest(method, url, version, headers, content, null);	// TODO add form content data parsing

        return request;
    }

    public void finishDecode(IoSession iosession, ProtocolDecoderOutput protocoldecoderoutput) throws Exception {
        // TODO Auto-generated method stub

    }
}
