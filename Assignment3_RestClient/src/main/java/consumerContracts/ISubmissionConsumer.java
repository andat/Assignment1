package consumerContracts;

import model.Submission;
import model.request.LoginModel;
import model.request.SubmissionRequestModel;

import java.util.List;

public interface ISubmissionConsumer {
    public List<Submission> getAllSubmissions(LoginModel credentials);
    public List<Submission> getSubmissionByAssignmentId(int assignId, LoginModel credentials);
    public boolean addSubmission(SubmissionRequestModel submission, LoginModel credentials);
    public boolean gradeSubmission(int id, int grade, LoginModel credentials);
    public boolean deleteSubmission(int id, LoginModel credentials);
    public List<Submission> getSubmissionsByUsername(String username, LoginModel credentials);
}
