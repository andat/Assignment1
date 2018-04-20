package com.example.Assignment2_LabApp.controller;

import com.example.Assignment2_LabApp.model.Student;
import com.example.Assignment2_LabApp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
//    @RequestMapping("/students")
//    public List<Student> getAllStudents(){
//        return "All Students";
//    }
}
