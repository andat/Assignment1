package com.example.Assignment2_LabApp.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Student extends User{

    @Column(name = "group_name", nullable = false)
    private String groupName;

    @Column(name = "hobby")
    private String hobby;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Attendance> attendances;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Submission> submissions;

    @Column(name = "password_set")

    private boolean passwordSet;

    public String getGroupName() {
        return groupName;
    }

    public String getHobby() {
        return hobby;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    public boolean isPasswordSet() {
        return passwordSet;
    }

    public void setPasswordSet(boolean passwordSet) {
        this.passwordSet = passwordSet;
    }
}
