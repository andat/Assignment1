package com.example.Assignment2_LabApp.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Laboratory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number", unique = true, nullable = false)
    private int number;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "title")
    private String title;

    @Column(name = "curricula")
    private String curricula;

    @Column(name = "description")
    private String description;

    @JsonBackReference
    @OneToMany(mappedBy="laboratory", orphanRemoval = true)//cascade = CascadeType.ALL;
    private List<Assignment> assignments;

    @JsonBackReference
    @OneToMany(mappedBy="laboratory", cascade = CascadeType.ALL)
    private List<Attendance> attendance;

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public Date getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getCurricula() {
        return curricula;
    }

    public String getDescription() {
        return description;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public List<Attendance> getAttendance() {
        return attendance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCurricula(String curricula) {
        this.curricula = curricula;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void setAttendance(List<Attendance> attendance) {
        this.attendance = attendance;
    }
}
