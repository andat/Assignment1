package consumerContracts;

import model.Submission;

import java.util.List;

public interface ISubmissionConsumer {
    public List<Submission> getAllSubmissions();
    public boolean deleteSubmission(int id);
}
