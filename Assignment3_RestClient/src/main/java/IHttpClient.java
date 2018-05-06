import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

import java.awt.image.ImagingOpException;
import java.io.IOException;

public interface IHttpClient {
    public HttpResponse getRequest(String url) throws IOException;
    public HttpResponse postRequest(String url, StringEntity body) throws IOException;
    public HttpResponse putRequest(String url, StringEntity body) throws IOException;
    public HttpResponse deleteRequest(String url) throws IOException;
}
