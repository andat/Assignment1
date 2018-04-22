package com.example.Assignment2_LabApp.service;

import com.example.Assignment2_LabApp.model.Assignment;
import com.example.Assignment2_LabApp.model.Submission;
import com.example.Assignment2_LabApp.repository.AssignmentRepository;
import com.example.Assignment2_LabApp.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService implements IAssignmentService{
    
    @Autowired
    private AssignmentRepository assignmentRepository;

    public List<Assignment> getAllAssignments(){
        return assignmentRepository.findAll();
    }

    public Assignment getAssignmentById(int id){
        Optional<Assignment> result = assignmentRepository.findById(id);
        return result.orElse(null);
    }

    public void addAssignment(Assignment assignment){
        assignmentRepository.save(assignment);
    }

    public void updateAssignment(Assignment assignment){
        assignmentRepository.save(assignment);
    }

    public void deleteAssignment(int id){
        Optional<Assignment> assignment = assignmentRepository.findById(id);
        if(assignment.isPresent())
            assignmentRepository.delete(assignment.get());
    }

    @Override
    public List<Assignment> getAssignmentsByLabId(int labId) {
        return assignmentRepository.getAssignmentsByLaboratoryId(labId);
    }
}
