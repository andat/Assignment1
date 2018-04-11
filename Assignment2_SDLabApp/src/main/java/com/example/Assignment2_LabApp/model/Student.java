package com.example.Assignment2_LabApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Student {

    @Column(name = "group", nullable = false)
    private String group;

    @Column(name = "hobby")
    private String hobby;

    public Student(String group, String hobby) {
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
