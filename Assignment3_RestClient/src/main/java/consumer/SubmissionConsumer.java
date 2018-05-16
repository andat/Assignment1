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

    public boolean addSubmission(SubmissionRequestModel sub, LoginModel credentials){
        boolean added = false;
        String url = "/submissions";
        try {
            String body = objectMapper.writeValueAsString(sub);
            added = HttpClient.postRequest(url, new StringEntity(body), credentials);
        } catch (IOException e){
            e.printStackTrace();
        }
        return added;
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
}
