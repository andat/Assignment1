package consumerContracts;

import model.Assignment;

import java.util.List;

public interface IAssignmentConsumer {
    public List<Assignment> getAllAssignments();
    public boolean deleteAssignment(int id);
}
