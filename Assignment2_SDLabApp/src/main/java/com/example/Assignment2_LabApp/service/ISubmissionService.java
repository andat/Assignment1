package com.example.Assignment2_LabApp.service;

import com.example.Assignment2_LabApp.model.Submission;

import java.util.List;

public interface ISubmissionService {
    public List<Submission> getAllSubmissions();
    public Submission getSubmissionById(int id);
    public void addSubmission(Submission submission);
    public void updateSubmission(Submission submission);
    public void deleteSubmission(int id);
}
