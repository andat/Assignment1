package com.example.Assignment2_LabApp.apimodel;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class AssignmentResponseModel {

    private int id;

    private String name;

    @JsonFormat(pattern = "YYYY-MM-DD")
    private Date deadline;

    private String description;

    private int laboratoryNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLaboratoryNumber() {
        return laboratoryNumber;
    }

    public void setLaboratoryNumber(int laboratoryNumber) {
        this.laboratoryNumber = laboratoryNumber;
    }
}
