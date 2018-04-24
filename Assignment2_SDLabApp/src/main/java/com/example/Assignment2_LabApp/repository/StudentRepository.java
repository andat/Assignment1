package com.example.Assignment2_LabApp.repository;

import com.example.Assignment2_LabApp.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository  extends JpaRepository<Student, Integer>{
}
