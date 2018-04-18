package com.example.Assignment2_LabApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Student extends User{

    @Column(name = "group", nullable = false)
    private String group;

    @Column(name = "hobby")
    private String hobby;

    public Student(String username, String password, String fullName, String email, String group, String hobby) {
        super(username, password, fullName, email, false);
        this.group = group;
        this.hobby = hobby;
    }

    public String getGroup() {
        return group;
    }

    public String getHobby() {
        return hobby;
    }
}
