package consumer;

import client.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Submission;
import consumerContracts.ISubmissionConsumer;
import model.request.LoginModel;
import model.request.StudentRequestModel;
import model.request.SubmissionRequestModel;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubmissionConsumer implements ISubmissionConsumer {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Submission> getAllSubmissions(LoginModel credentials) {
        String url = "/submissions";
        List<Submission> submissions = new ArrayList<>();
        try{
            String response = HttpClient.getRequest(url, credentials);
            submissions = Arrays.asList(objectMapper.readValue(response, Submission[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return submissions;
    }

    @Override
    public List<Submission> getSubmissionByAssignmentId(int assignId, LoginModel credentials) {
        String url = "/submissions/assignments/" + assignId;
        List<Submission> submissions = new ArrayList<>();
        try{
            String response = HttpClient.getRequest(url, credentials);
            submissions = Arrays.asList(objectMapper.readValue(response, Submission[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return submissions;
    }

    @Override
    public boolean gradeSubmission(int id, int grade, LoginModel credentials) {
        boolean graded = false;
        String url = "/submissions/" + id + "/" + grade;
        try{
            graded = HttpClient.putRequest(url, credentials);
        } catch (IOException e){
            e.printStackTrace();
        }
        return graded;
    }

    public String addSubmission(SubmissionRequestModel sub, LoginModel credentials){
        String response = null;
        String url = "/submissions";
        try {
            String body = objectMapper.writeValueAsString(sub);
            response = HttpClient.postRequestWithResponse(url, new StringEntity(body));
        } catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public boolean deleteSubmission(int id, LoginModel credentials) {
        String url = "/submissions/" + id;
        Boolean deleted = false;
        try {
            deleted = HttpClient.deleteRequest(url, credentials);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deleted;
    }

    @Override
    public List<Submission> getSubmissionsByUsername(String username, LoginModel credentials) {
        String url = "/submissions/students/" + username;
        List<Submission> submissions = new ArrayList<>();
        try{
            String response = HttpClient.getRequest(url, credentials);
            submissions = Arrays.asList(objectMapper.readValue(response, Submission[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return submissions;
    }
}
