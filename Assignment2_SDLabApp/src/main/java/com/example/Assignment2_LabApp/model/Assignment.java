package com.example.Assignment2_LabApp.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="lab_id")
    private Laboratory laboratory;

    @OneToMany(mappedBy="assignment")
    private Set<Submission> submissions;

    public Assignment(String name, Date deadline, String description, Laboratory laboratory) {
        this.name = name;
        this.deadline = deadline;
        this.description = description;
        this.laboratory = laboratory;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public Set<Submission> getSubmissions() {
        return submissions;
    }
}
