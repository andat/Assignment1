package consumerContracts;

import model.Submission;

import java.util.List;

public interface ISubmissionConsumer {
    public List<Submission> getAllSubmissions();
    public boolean gradeSubmission(int id, int grade);
    public boolean deleteSubmission(int id);
}
