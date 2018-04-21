package com.example.Assignment2_LabApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Student extends User{

    @Column(name = "group_name", nullable = false)
    private String groupName;

    @Column(name = "hobby")
    private String hobby;

    @OneToMany(mappedBy = "student")
    private List<Attendance> attendances;

    @OneToMany(mappedBy = "student")
    private List<Submission> submissions;

    public String getGroupName() {
        return groupName;
    }

    public String getHobby() {
        return hobby;
    }
}
