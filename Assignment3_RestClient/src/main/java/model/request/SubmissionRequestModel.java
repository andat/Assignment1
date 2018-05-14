package model.request;

public class SubmissionRequestModel {
    private int assignmentId;

    private int studentId;

    private String description;

    public SubmissionRequestModel(int assignmentId, int studentId, String description) {
        this.assignmentId = assignmentId;
        this.studentId = studentId;
        this.description = description;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
