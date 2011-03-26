package er.woadaptor._private;
import com.webobjects.appserver.WOResponse;


public class ResponseWrapper {

    private WOResponse _response;

    public ResponseWrapper(WOResponse response) {
        _response = response;
    }

    public WOResponse response() {
        return _response;
    }

}
