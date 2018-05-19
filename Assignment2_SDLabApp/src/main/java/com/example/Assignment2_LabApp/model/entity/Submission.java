package com.example.Assignment2_LabApp.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Date;

@Entity
public class Submission {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    //@JsonManagedReference
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "student_id")
    //@JsonManagedReference
    private Student student;

    @Min(0) @Max(10)
    @Column(name = "grade")
    private int grade;

    @Column(name = "date")
    private Date date;

    @Column(name = "description")
    private String description;

    public int getId() {
        return id;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public Student getStudent() {
        return student;
    }

    public int getGrade() {
        return grade;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
