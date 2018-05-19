package client;

import model.request.LoginModel;
import org.apache.http.HttpHeaders;
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
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HttpClient{
    private static String host = "http://localhost:8080";

    public static String getRequest(String url, LoginModel credentials) throws IOException{
            try(CloseableHttpClient client = HttpClients.createDefault()){
                HttpGet httpGet = new HttpGet(host + url);

                String authStr = credentials.getUsername() + ":" + credentials.getPassword();
                String authHeader = Base64.getEncoder().encodeToString((authStr).getBytes());
                httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + authHeader);

                HttpResponse response = client.execute(httpGet);

                //ResponseHandler<String> responseHandler=new BasicResponseHandler();
                String responseBody = EntityUtils.toString(client.execute(httpGet).getEntity());
                return responseBody;
            }
    }

    public static int postRequest(String url, StringEntity body, LoginModel credentials) throws IOException{
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(host + url);
            httpPost.setEntity(body);

            String authStr = credentials.getUsername() + ":" + credentials.getPassword();
            String authHeader = Base64.getEncoder().encodeToString((authStr).getBytes());
            httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + authHeader);

            httpPost.setHeader("Content-type", "application/json");
            HttpResponse response = client.execute(httpPost);

            System.out.println("POST - response code: " + response.getStatusLine().getStatusCode());
            return response.getStatusLine().getStatusCode();
        }
    }

    public static String postRequestWithResponse(String url, StringEntity body, LoginModel credentials) throws IOException{
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(host + url);
            httpPost.setEntity(body);

            String authStr = credentials.getUsername() + ":" + credentials.getPassword();
            String authHeader = Base64.getEncoder().encodeToString((authStr).getBytes());
            httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + authHeader);

            httpPost.setHeader("Content-type", "application/json");
            HttpResponse response = client.execute(httpPost);

            //TODO comment out
            System.out.println("POST - response code: " + response.getStatusLine().getStatusCode());
            if(response.getStatusLine().getStatusCode() == 200)
                return EntityUtils.toString(response.getEntity());
            else
                return null;
        }
    }

    public static String postRequestWithResponse(String url, StringEntity body) throws IOException{
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(host + url);
            httpPost.setEntity(body);


            httpPost.setHeader("Content-type", "application/json");
            HttpResponse response = client.execute(httpPost);

            //TODO comment out
            System.out.println("POST - response code: " + response.getStatusLine().getStatusCode());
            if(response.getStatusLine().getStatusCode() == 200)
                return EntityUtils.toString(response.getEntity());
            else
                return null;
        }
    }

    public static boolean putRequest(String url, StringEntity body, LoginModel credentials) throws IOException {
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(host + url);
            httpPut.setEntity(body);

            String authStr = credentials.getUsername() + ":" + credentials.getPassword();
            String authHeader = Base64.getEncoder().encodeToString((authStr).getBytes());
            httpPut.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + authHeader);

            httpPut.setHeader("Content-type", "application/json");
            HttpResponse response = client.execute(httpPut);

            System.out.println("PUT - response: " + response.getStatusLine());
            if(response.getStatusLine().getStatusCode() == 200)
                return true;
            else
                return false;
        }
    }

    public static boolean putRequest(String url, LoginModel credentials) throws IOException {
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(host + url);

            String authStr = credentials.getUsername() + ":" + credentials.getPassword();
            String authHeader = Base64.getEncoder().encodeToString((authStr).getBytes());
            httpPut.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + authHeader);

            httpPut.setHeader("Content-type", "application/json");
            HttpResponse response = client.execute(httpPut);

            System.out.println("PUT - response: " + response.getStatusLine());
            if(response.getStatusLine().getStatusCode() == 200)
                return true;
            else
                return false;
        }
    }


    public static boolean deleteRequest(String url, LoginModel credentials) throws IOException {
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpDelete httpDelete = new HttpDelete(host + url);

            String authStr = credentials.getUsername() + ":" + credentials.getPassword();
            String authHeader = Base64.getEncoder().encodeToString((authStr).getBytes());
            httpDelete.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + authHeader);

            HttpResponse response = client.execute(httpDelete);

            System.out.println("DELETE - response: " + response.getStatusLine());
            if(response.getStatusLine().getStatusCode() == 200)
                return true;
            else
                return false;
        }
    }
}
