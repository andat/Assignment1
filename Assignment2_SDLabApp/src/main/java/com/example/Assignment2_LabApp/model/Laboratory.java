package com.example.Assignment2_LabApp.model;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy="laboratory", cascade = CascadeType.ALL)
    private List<Assignment> assignments;

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
}
