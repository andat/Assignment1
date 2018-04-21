package com.example.Assignment2_LabApp.controller;

import com.example.Assignment2_LabApp.model.Attendance;
import com.example.Assignment2_LabApp.service.IAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Attendance getAttendanceById(@PathVariable int id){
        return attendanceService.getAttendanceById(id);
    }

    @RequestMapping(method = POST)
    public void addAttendance(@RequestBody Attendance attendance){
        attendanceService.addAttendance(attendance);
    }

    @RequestMapping(method = PUT, value = "/{id}")
    public void updateAttendance(@RequestBody Attendance attendance, @PathVariable int id){
        //TODO check if user exists
        attendanceService.updateAttendance(attendance);
    }

    @RequestMapping(method = DELETE, value = "/{id}")
    public void deleteAttendance(@PathVariable int id){
        attendanceService.deleteAttendance(id);
    }
}
