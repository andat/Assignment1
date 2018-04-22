package com.example.Assignment2_LabApp.apimodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Date;

public class LabRequestModel {

    @Min(1) @Max(14)
    private int number;

    //TODO figure out why json format for day is not correct

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private Date date;

    private String title;

    private String curricula;

    private String description;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurricula() {
        return curricula;
    }

    public void setCurricula(String curricula) {
        this.curricula = curricula;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
