package consumerContracts;

import model.Submission;
import model.request.SubmissionRequestModel;

import java.util.List;

public interface ISubmissionConsumer {
    public List<Submission> getAllSubmissions();
    public List<Submission> getSubmissionByAssignmentId(int assignId);
    public boolean addSubmission(SubmissionRequestModel submission);
    public boolean gradeSubmission(int id, int grade);
    public boolean deleteSubmission(int id);
}
