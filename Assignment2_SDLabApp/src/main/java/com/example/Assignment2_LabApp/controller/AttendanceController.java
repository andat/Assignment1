package com.example.Assignment2_LabApp.controller;

import com.example.Assignment2_LabApp.model.Attendance;
import com.example.Assignment2_LabApp.service.IAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

    @Autowired
    private IAttendanceService attendanceService;

    @RequestMapping(method = GET)
    public List<Attendance> getAllAttendances(){
        return attendanceService.getAllAttendances();
    }

    @RequestMapping(method = GET, value = "/{id}")
    public ResponseEntity getAttendanceById(@PathVariable int id){
        Attendance a = attendanceService.getAttendanceById(id);
        if(a == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance not found.");
        else
            return ResponseEntity.ok(a);
    }

    @RequestMapping(method = POST)
    public ResponseEntity addAttendance(@Validated @RequestBody Attendance attendance){
        attendanceService.addAttendance(attendance);
        return ResponseEntity.ok("New attendance added.");
    }

    @RequestMapping(method = PUT, value = "/{id}")
    public ResponseEntity updateAttendance(@Validated @RequestBody Attendance attendance, @PathVariable int id){
        Attendance a = attendanceService.getAttendanceById(id);
        if(a == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance not found.");
        else {
            attendance.setId(id);
            attendanceService.updateAttendance(attendance);
            return ResponseEntity.ok("Attendance updated.");
        }
    }

    @RequestMapping(method = DELETE, value = "/{id}")
    public ResponseEntity deleteAttendance(@PathVariable int id){
        Attendance a = attendanceService.getAttendanceById(id);
        if(a == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance not found.");
        else {
            attendanceService.deleteAttendance(id);
            return ResponseEntity.ok("Attendance deleted.");
        }
    }

    @RequestMapping(method = GET, value = "/labs/{labId}")
    public List<Attendance> getAttendanceByLabId(@PathVariable int labId){
        return attendanceService.getAttendanceByLabId(labId);
    }
}
