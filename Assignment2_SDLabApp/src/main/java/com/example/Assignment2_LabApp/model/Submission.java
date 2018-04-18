package com.example.Assignment2_LabApp.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Submission {
    @Id
    @GeneratedValue
    private int id;

    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @JoinColumn(name = "student_id")
    private Student student;
    
    @Column(name = "grade")
    private int grade;

    @Column(name = "date")
    private Date date;

    @Column(name = "description")
    private String description;

    public Submission(Assignment assignment, Student student, int grade, Date date, String description) {
        this.assignment = assignment;
        this.student = student;
        this.grade = grade;
        this.date = date;
        this.description = description;
    }

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
}
