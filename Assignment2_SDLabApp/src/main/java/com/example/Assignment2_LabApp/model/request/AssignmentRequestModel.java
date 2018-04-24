package com.example.Assignment2_LabApp.model.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class AssignmentRequestModel {

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    private String description;

    private int laboratoryId;

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

    public int getLaboratoryId() {
        return laboratoryId;
    }

    public void setLaboratoryId(int laboratoryId) {
        this.laboratoryId = laboratoryId;
    }
}