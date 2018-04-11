package com.example.Assignment2_LabApp.model;

import jdk.nashorn.internal.ir.Assignment;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Laboratory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "labNumber", unique = true, nullable = false)
    private int labNumber;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "title")
    private String title;

    @Column(name = "curricula")
    private String curricula;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "laboratory")
    private List<Assignment> assignments;

    public Laboratory(int labNumber, Date date, String title, String curricula, String description, List<Assignment> assignments) {
        this.labNumber = labNumber;
        this.date = date;
        this.title = title;
        this.curricula = curricula;
        this.description = description;
        this.assignments = assignments;
    }

    public int getId() {
        return id;
    }

    public int getLabNumber() {
        return labNumber;
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
}
