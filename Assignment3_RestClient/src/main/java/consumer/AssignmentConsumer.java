package consumer;

import client.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Assignment;
import consumerContracts.IAssignmentConsumer;
import model.request.AssignmentRequestModel;
import model.request.LoginModel;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssignmentConsumer implements IAssignmentConsumer {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Assignment> getAllAssignments(LoginModel credentials) {
        String url = "/assignments";
        List<Assignment> assignmentList = new ArrayList<>();
        try{
            String response = HttpClient.getRequest(url, credentials);
            Assignment[] assignments = objectMapper.readValue(response, Assignment[].class);
            assignmentList = Arrays.asList(assignments);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return assignmentList;
    }

    @Override
    public List<Assignment> getAssignmentsByLabId(int labId, LoginModel credentials) {
        String url = "/assignments/labs/" + labId;
        List<Assignment> assignmentList = null;
        try{
            String response = HttpClient.getRequest(url, credentials);
            Assignment[] assignments = objectMapper.readValue(response, Assignment[].class);
            assignmentList = Arrays.asList(assignments);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return assignmentList;
    }

    public boolean addAssignment(AssignmentRequestModel assign, LoginModel credentials){
        boolean added = false;
        String url = "/assignments";
        try {
            String body = objectMapper.writeValueAsString(assign);
            added = HttpClient.postRequest(url, new StringEntity(body), credentials);
        } catch (IOException e){
            e.printStackTrace();
        }
        return added;
    }

    @Override
    public boolean editAssignment(AssignmentRequestModel a, int id, LoginModel credentials) {
        boolean edited = false;
        String url = "/assignments/" + id;
        try{
            String body = objectMapper.writeValueAsString(a);
            edited = HttpClient.putRequest(url, new StringEntity(body), credentials);
        } catch (IOException e){
            e.printStackTrace();
        }
        return edited;
    }

    @Override
    public boolean deleteAssignment(int id, LoginModel credentials) {
        boolean deleted = false;
        String url = "/assignments/" + id;
        try{
            deleted = HttpClient.deleteRequest(url, credentials);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deleted;
    }
}
