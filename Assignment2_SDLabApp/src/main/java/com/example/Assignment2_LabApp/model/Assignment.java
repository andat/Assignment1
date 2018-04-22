package com.example.Assignment2_LabApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

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
    @JsonManagedReference
    private Laboratory laboratory;

    @OneToMany(mappedBy="assignment")
    @JsonBackReference
    private Set<Submission> submissions;

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

    public void setSubmissions(Set<Submission> submissions) {
        this.submissions = submissions;
    }

    public void setId(int id){
        this.id = id;
    }
}
