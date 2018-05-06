import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpClient implements IHttpClient{

    @Override
    public HttpResponse getRequest(String url) throws IOException{
            try(CloseableHttpClient client = HttpClients.createDefault()){
                HttpGet httpGet = new HttpGet(url);
                HttpResponse response = client.execute(httpGet);

                //TODO comment out
                System.out.println("GET - response code: " + response.getStatusLine().getStatusCode());

                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

                System.out.println(result);
                return response;
            }
    }

    @Override
    public HttpResponse postRequest(String url, StringEntity body) throws IOException{
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(body);
            HttpResponse response = client.execute(httpPost);

            //TODO comment out
            System.out.println("POST - response code: " + response.getStatusLine().getStatusCode());
            return response;
        }
    }

    @Override
    public HttpResponse putRequest(String url, StringEntity body) throws IOException {
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(url);
            httpPut.setEntity(body);
            HttpResponse response = client.execute(httpPut);

            System.out.println("PUT - response: " + response.getStatusLine());
            return response;
        }
    }

    @Override
    public HttpResponse deleteRequest(String url) throws IOException {
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpDelete httpDelete = new HttpDelete(url);
            HttpResponse response = client.execute(httpDelete);

            System.out.println("DELETE - response: " + response.getStatusLine());
            return response;
        }
    }
}
