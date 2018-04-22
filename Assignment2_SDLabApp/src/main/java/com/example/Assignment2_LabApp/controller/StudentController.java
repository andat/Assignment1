package com.example.Assignment2_LabApp.controller;

import com.example.Assignment2_LabApp.apimodel.StudentRequestModel;
import com.example.Assignment2_LabApp.apimodel.StudentResponseModel;
import com.example.Assignment2_LabApp.model.Student;
import com.example.Assignment2_LabApp.service.IStudentService;
import com.example.Assignment2_LabApp.util.TokenGenerator;
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
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = GET)
    public List<StudentResponseModel> getAllStudents(){
        return studentService.getAllStudents().stream()
                                              .map(stud -> modelMapper.map(stud, StudentResponseModel.class))
                                              .collect(Collectors.toList());
    }

    @RequestMapping(method = GET, value = "/{id}")
    public ResponseEntity getStudentById(@PathVariable int id){
        Student stud = studentService.getStudentById(id);
        if(stud != null)
            return ResponseEntity.ok(modelMapper.map(stud, StudentResponseModel.class));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found.");
    }

    @RequestMapping(method = POST)
    public ResponseEntity addStudent(@Validated @RequestBody StudentRequestModel student){
        Student stud = modelMapper.map(student, Student.class);
        stud.setIsTeacher(false);
        System.out.println(student.getGroupName());
        studentService.addStudent(stud);
        return ResponseEntity.ok("New student added.");
    }

    @RequestMapping(method = PUT, value = "/{id}")
    public ResponseEntity updateStudent(@RequestBody StudentRequestModel student, int id){
        Student oldStud = studentService.getStudentById(id);
        if(oldStud != null) {
            Student newStud= modelMapper.map(student, Student.class);
            newStud.setId(id);
            newStud.setIsTeacher(false);
            newStud.setPassword(oldStud.getPassword());
            studentService.updateStudent(newStud);
            return ResponseEntity.ok("Student updated");
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found.");
    }

    @RequestMapping(method = DELETE, value = "/{id}")
    public ResponseEntity deleteStudent(@PathVariable int id){
        if(studentService.getStudentById(id) != null){
            studentService.deleteStudent(id);
            return ResponseEntity.ok().body("Student deleted.");
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found.");
    }

    @RequestMapping(method = PUT, value = "/{id}/password")
    public ResponseEntity changePassword(@PathVariable int id, @PathVariable String password){
        Student stud = studentService.getStudentById(id);
        if(stud == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found.");
        else {
            //TODO add authentication part
            studentService.changePassword(stud, password);
            return ResponseEntity.ok("Password changed.");
        }
    }
}
