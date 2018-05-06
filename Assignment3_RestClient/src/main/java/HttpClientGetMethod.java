import controller.StudentTableController;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import service.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpClientGetMethod {

    public static void main(String[] args) throws IOException{
//        try(CloseableHttpClient client = HttpClients.createDefault()){
//            HttpGet httpGet = new HttpGet("http://localhost:8080/students");
//
//            HttpResponse response = client.execute(httpGet);
//
//            System.out.println("response code: " + response.getStatusLine().getStatusCode());
//
//            BufferedReader rd = new BufferedReader(
//                    new InputStreamReader(response.getEntity().getContent()));
//
//            StringBuffer result = new StringBuffer();
//            String line = "";
//            while ((line = rd.readLine()) != null) {
//                result.append(line);
//            }
//
//            System.out.println(result);
//        }
        StudentService service = new StudentService();
        service.getAllStudents();
    }
}
