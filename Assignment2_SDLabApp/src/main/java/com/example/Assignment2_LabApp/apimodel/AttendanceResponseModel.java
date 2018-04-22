package com.example.Assignment2_LabApp.apimodel;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class AttendanceResponseModel {

    private int id;

    private int laboratoryNumber;

    private String laboratoryTitle;

    private String studentUsername;

    @JsonFormat(pattern = "YYYY-MM-DD")
    private Date laboratoryDate;

    private boolean present;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLaboratoryNumber() {
        return laboratoryNumber;
    }

    public void setLaboratoryNumber(int laboratoryNumber) {
        this.laboratoryNumber = laboratoryNumber;
    }

    public String getLaboratoryTitle() {
        return laboratoryTitle;
    }

    public void setLaboratoryTitle(String laboratoryTitle) {
        this.laboratoryTitle = laboratoryTitle;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }

    public Date getLaboratoryDate() {
        return laboratoryDate;
    }

    public void setLaboratoryDate(Date laboratoryDate) {
        this.laboratoryDate = laboratoryDate;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}
