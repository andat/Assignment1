package consumerContracts;

import model.Assignment;
import model.request.AssignmentRequestModel;

import java.util.List;

public interface IAssignmentConsumer {
    public List<Assignment> getAllAssignments();
    public List<Assignment> getAssignmentsByLabId(int labId);
    public boolean addAssignment(AssignmentRequestModel a);
    public boolean editAssignment(AssignmentRequestModel a, int id);
    public boolean deleteAssignment(int id);
}
