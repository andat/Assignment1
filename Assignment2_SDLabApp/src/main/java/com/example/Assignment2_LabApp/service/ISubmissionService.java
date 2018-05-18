package com.example.Assignment2_LabApp.service;

import com.example.Assignment2_LabApp.model.entity.Submission;

import java.sql.Date;
import java.util.List;

public interface ISubmissionService {
    public List<Submission> getAllSubmissions();
    public Submission getSubmissionById(int id);
    public void addSubmission(Submission submission);
    public void updateSubmission(Submission submission);
    public void gradeSubmission(Submission submission, int grade);
    public void deleteSubmission(int id);
    public List<Submission> getSubmissionsByAssignmentId(int assignmentId);
    public List<Submission> getSubmissionsByUsername(String username);
    public boolean checkValidSubmission(Submission submission, Date deadline);
    public boolean checkMaxNoOfSubmissionsReached(Submission submission, int max);
}
