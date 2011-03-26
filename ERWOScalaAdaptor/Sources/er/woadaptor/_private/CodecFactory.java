package er.woadaptor._private;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

public class CodecFactory extends DemuxingProtocolCodecFactory {
    public CodecFactory() {
        addMessageDecoder(RequestDecoder.class);
        addMessageEncoder(ResponseWrapper.class, ResponseEncoder.class);
    }
}
