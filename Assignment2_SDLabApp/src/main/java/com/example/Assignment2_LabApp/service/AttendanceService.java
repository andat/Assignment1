package com.example.Assignment2_LabApp.service;

import com.example.Assignment2_LabApp.model.Attendance;
import com.example.Assignment2_LabApp.model.Submission;
import com.example.Assignment2_LabApp.repository.AttendanceRepository;
import com.example.Assignment2_LabApp.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService implements IAttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    public List<Attendance> getAllAttendances(){
        return attendanceRepository.findAll();
    }

    public Attendance getAttendanceById(int id){
        Optional<Attendance> result = attendanceRepository.findById(id);
        return result.orElse(null);
    }

    public void addAttendance(Attendance attendance){
        attendanceRepository.save(attendance);
    }

    public void updateAttendance(Attendance attendance){
        attendanceRepository.save(attendance);
    }

    public void deleteAttendance(int id){
        Optional<Attendance> attendance = attendanceRepository.findById(id);
        if(attendance.isPresent())
            attendanceRepository.delete(attendance.get());
    }

    @Override
    public boolean checkIfAlreadyExists(Attendance attendance) {
        int labId = attendance.getLaboratory().getId();
        int studentId = attendance.getStudent().getId();
        if(attendanceRepository.getAttendanceByLaboratoryIdAndStudentId(labId, studentId) != null)
            return true;
        else
            return false;
    }

    @Override
    public List<Attendance> getAttendanceByLabId(int labId) {
        return attendanceRepository.getAttendanceByLaboratoryId(labId);
    }
}
