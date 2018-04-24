package com.example.Assignment2_LabApp.repository;

import com.example.Assignment2_LabApp.model.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    public List<Submission> getSubmissionsByAssignmentId(int assignmentId);
    public List<Submission> getSubmissionsByAssignmentIdAndStudentId(int assignmentId, int studentId);
}
