package com.example.Assignment2_LabApp.repository;

import com.example.Assignment2_LabApp.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
}
