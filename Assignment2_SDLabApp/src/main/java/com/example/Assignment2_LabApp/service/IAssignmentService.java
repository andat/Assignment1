package com.example.Assignment2_LabApp.service;

import com.example.Assignment2_LabApp.model.Assignment;

import java.util.List;

public interface IAssignmentService {

    public List<Assignment> getAllAssignments();
    public Assignment getAssignmentById(int id);
    public void addAssignment(Assignment assignment);
    public void updateAssignment(Assignment assignment);
    public void deleteAssignment(int id);
    public List<Assignment> getAssignmentsByLabId(int labId);
}
