package com.example.Assignment2_LabApp.repository;

import com.example.Assignment2_LabApp.model.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    public List<Attendance> getAttendanceByLaboratoryId(int labId);
    public Attendance getAttendanceByLaboratoryIdAndStudentId(int labId, int studentId);
}
