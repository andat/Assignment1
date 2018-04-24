package com.example.Assignment2_LabApp.repository;

import com.example.Assignment2_LabApp.model.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    public List<Assignment> getAssignmentsByLaboratoryId(int labId);
}

