package consumerContracts;

import model.Assignment;
import model.request.AssignmentRequestModel;
import model.request.LoginModel;

import java.util.List;

public interface IAssignmentConsumer {
    public List<Assignment> getAllAssignments(LoginModel credentials);
    public List<Assignment> getAssignmentsByLabId(int labId, LoginModel credentials);
    public boolean addAssignment(AssignmentRequestModel a, LoginModel credentials);
    public boolean editAssignment(AssignmentRequestModel a, int id, LoginModel credentials);
    public boolean deleteAssignment(int id, LoginModel credentials);
}
