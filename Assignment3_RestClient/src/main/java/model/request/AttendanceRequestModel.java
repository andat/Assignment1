package model.request;

public class AttendanceRequestModel {
    private int laboratoryId;
    private int studentId;
    private boolean present;

    public AttendanceRequestModel(int laboratoryId, int studentId, boolean present) {
        this.laboratoryId = laboratoryId;
        this.studentId = studentId;
        this.present = present;
    }

    public int getLaboratoryId() {
        return laboratoryId;
    }

    public void setLaboratoryId(int laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}
