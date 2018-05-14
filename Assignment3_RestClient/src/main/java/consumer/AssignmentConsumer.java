package consumer;

import client.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Assignment;
import consumerContracts.IAssignmentConsumer;
import model.request.AssignmentRequestModel;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AssignmentConsumer implements IAssignmentConsumer {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Assignment> getAllAssignments() {
        String url = "/assignments";
        List<Assignment> assignmentList = null;
        try{
            String response = HttpClient.getRequest(url);
            Assignment[] assignments = objectMapper.readValue(response, Assignment[].class);
            assignmentList = Arrays.asList(assignments);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return assignmentList;
    }

    public boolean addAssignment(AssignmentRequestModel assign){
        boolean added = false;
        String url = "/assignments";
        try {
            String body = objectMapper.writeValueAsString(assign);
            added = HttpClient.postRequest(url, new StringEntity(body));
        } catch (IOException e){
            e.printStackTrace();
        }
        return added;
    }

    @Override
    public boolean deleteAssignment(int id) {
        boolean deleted = false;
        String url = "assignments/" + id;
        try{
            deleted = HttpClient.deleteRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deleted;
    }
}
