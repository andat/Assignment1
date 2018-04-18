package com.example.Assignment2_LabApp.model;

import javax.persistence.*;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "lab_id")
    private Laboratory laboratory;

    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "present")
    private boolean present;

    public Attendance(Laboratory laboratory, Student student, boolean present) {
        this.laboratory = laboratory;
        this.student = student;
        this.present = present;
    }

    public int getId() {
        return id;
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public Student getStudent() {
        return student;
    }

    public boolean isPresent() {
        return present;
    }
}
