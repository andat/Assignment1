package com.example.Assignment2_LabApp.apimodel;

public class AttendanceResponseModel {

    private int id;

    private int laboratoryId;

    private int studentId;

    private boolean present;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getlaboratoryId() {
        return laboratoryId;
    }

    public void setlaboratoryId(int laboratoryId) {
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
