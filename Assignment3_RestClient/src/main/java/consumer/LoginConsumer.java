package consumer;

import client.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import consumerContracts.ILoginConsumer;
import model.request.LoginModel;
import org.apache.http.entity.StringEntity;

import java.io.IOException;

public class LoginConsumer implements ILoginConsumer {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String login(String username, String password) {
        String role = null;
        String url = "/login";
        LoginModel credentials = new LoginModel(username, password);
        try{
            String body = mapper.writeValueAsString(credentials);
            role = HttpClient.postRequestWithResponse(url, new StringEntity(body));
            System.out.println("Logged in as: " + role);
        } catch(IOException e){
            e.printStackTrace();
        }
        return role;
    }
}
