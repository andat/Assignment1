package com.example.Assignment2_LabApp.controller;

import com.example.Assignment2_LabApp.model.Student;
import com.example.Assignment2_LabApp.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @RequestMapping(method = GET)
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @RequestMapping(method = GET, value = "/{id}")
    public Student getUserById(@PathVariable int id){
        return studentService.getStudentById(id);
    }

    @RequestMapping(method = POST)
    public void addStudent(@RequestBody Student student){
        studentService.addStudent(student);
    }

    @RequestMapping(method = PUT, value = "/{id}")
    public void updateStudent(@RequestBody Student student){
        //TODO check if Student exists
        studentService.updateStudent(student);
    }

    @RequestMapping(method = DELETE, value = "/{id}")
    public void deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);
    }
}
