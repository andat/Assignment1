package com.example.Assignment2_LabApp.repository;

import com.example.Assignment2_LabApp.model.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabRepository extends JpaRepository<Laboratory, Integer> {
}
