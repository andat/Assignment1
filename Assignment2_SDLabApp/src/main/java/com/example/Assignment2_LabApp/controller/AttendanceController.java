package com.example.Assignment2_LabApp.controller;

import com.example.Assignment2_LabApp.model.request.AttendanceRequestModel;
import com.example.Assignment2_LabApp.model.response.AttendanceResponseModel;
import com.example.Assignment2_LabApp.model.entity.Attendance;
import com.example.Assignment2_LabApp.service.IAttendanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

    @Autowired
    private IAttendanceService attendanceService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = GET)
    public List<AttendanceResponseModel> getAllAttendances(){
        return attendanceService.getAllAttendances().stream()
                                                    .map(attendance -> modelMapper.map(attendance, AttendanceResponseModel.class))
                                                    .collect(Collectors.toList());
    }

    @RequestMapping(method = GET, value = "/{id}")
    public ResponseEntity getAttendanceById(@PathVariable int id){
        Attendance a = attendanceService.getAttendanceById(id);
        if(a == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance not found.");
        else
            return ResponseEntity.ok(modelMapper.map(a, AttendanceResponseModel.class));
    }

    @RequestMapping(method = POST)
    public ResponseEntity addAttendance(@RequestBody AttendanceRequestModel attendance){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Attendance a = modelMapper.map(attendance, Attendance.class);
        if(attendanceService.checkIfAlreadyExists(a)){
            return ResponseEntity.badRequest().body("Attendance already exists.");
        } else {
            attendanceService.addAttendance(a);
            return ResponseEntity.ok("New attendance added.");
        }
    }

    @RequestMapping(method = PUT, value = "/{id}")
    public ResponseEntity updateAttendance(@Validated @RequestBody AttendanceRequestModel attendance, @PathVariable int id){
        if(attendanceService.getAttendanceById(id) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance not found.");
        else {
            Attendance a = modelMapper.map(attendance, Attendance.class);
            a.setId(id);
            attendanceService.updateAttendance(a);
            return ResponseEntity.ok("Attendance updated.");
        }
    }

    @RequestMapping(method = DELETE, value = "/{id}")
    public ResponseEntity deleteAttendance(@PathVariable int id){
        if(attendanceService.getAttendanceById(id) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance not found.");
        else {
            attendanceService.deleteAttendance(id);
            return ResponseEntity.ok("Attendance deleted.");
        }
    }

    @RequestMapping(method = GET, value = "/labs/{labId}")
    public List<AttendanceResponseModel> getAttendanceByLabId(@PathVariable int labId){
        return attendanceService.getAttendanceByLabId(labId).stream()
                                                            .map(a -> modelMapper.map(a, AttendanceResponseModel.class))
                                                            .collect(Collectors.toList());
    }
}
