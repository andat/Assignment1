package com.example.Assignment2_LabApp.service;

import com.example.Assignment2_LabApp.model.Submission;
import com.example.Assignment2_LabApp.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService implements ISubmissionService {
    @Autowired
    private SubmissionRepository submissionRepository;

    public List<Submission> getAllSubmissions(){
        return submissionRepository.findAll();
    }

    public Submission getSubmissionById(int id){
        Optional<Submission> result = submissionRepository.findById(id);
        return result.orElse(null);
    }

    public void addSubmission(Submission submission){
        submissionRepository.save(submission);
    }

    public void updateSubmission(Submission submission){
        submissionRepository.save(submission);
    }

    public void deleteSubmission(int id){
        Optional<Submission> submission = submissionRepository.findById(id);
        if(submission.isPresent())
            submissionRepository.delete(submission.get());
    }

    @Override
    public List<Submission> getSubmissionsByAssignmentId(int assignmentId) {
        return submissionRepository.getSubmissionsByAssignmentId(assignmentId);
    }
}
