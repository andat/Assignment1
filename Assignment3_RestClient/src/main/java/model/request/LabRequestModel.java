package model.request;

import java.sql.Date;

public class LabRequestModel {

    private int number;

    private Date date;

    private String title;

    private String curricula;

    private String description;

    public LabRequestModel(int number, Date date, String title, String curricula, String description) {
        this.number = number;
        this.date = date;
        this.title = title;
        this.curricula = curricula;
        this.description = description;
    }

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
