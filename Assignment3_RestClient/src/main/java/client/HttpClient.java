package client;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpClient{
    private static String host = "http://localhost:8080";

    public static String getRequest(String url) throws IOException{
            try(CloseableHttpClient client = HttpClients.createDefault()){
                HttpGet httpGet = new HttpGet(host + url);
                HttpResponse response = client.execute(httpGet);

                ResponseHandler<String> responseHandler=new BasicResponseHandler();
                String responseBody = client.execute(httpGet, responseHandler);
                return responseBody;
            }
    }

    public static boolean postRequest(String url, StringEntity body) throws IOException{
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(host + url);
            httpPost.setEntity(body);
            httpPost.setHeader("Content-type", "application/json");
            HttpResponse response = client.execute(httpPost);

            //TODO comment out
            System.out.println("POST - response code: " + response.getStatusLine().getStatusCode());
            if(response.getStatusLine().getStatusCode() == 200)
                return true;
            else
                return false;
        }
    }

    public static boolean putRequest(String url, StringEntity body) throws IOException {
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(host + url);
            httpPut.setEntity(body);
            httpPut.setHeader("Content-type", "application/json");
            HttpResponse response = client.execute(httpPut);

            System.out.println("PUT - response: " + response.getStatusLine());
            if(response.getStatusLine().getStatusCode() == 200)
                return true;
            else
                return false;
        }
    }


    public static boolean deleteRequest(String url) throws IOException {
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpDelete httpDelete = new HttpDelete(host + url);
            HttpResponse response = client.execute(httpDelete);

            System.out.println("DELETE - response: " + response.getStatusLine());
            if(response.getStatusLine().getStatusCode() == 200)
                return true;
            else
                return false;
        }
    }
}
