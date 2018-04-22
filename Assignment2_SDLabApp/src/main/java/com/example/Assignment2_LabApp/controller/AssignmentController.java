package com.example.Assignment2_LabApp.controller;

import com.example.Assignment2_LabApp.model.Assignment;
import com.example.Assignment2_LabApp.service.IAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    @Autowired
    private IAssignmentService assignmentService;

    @RequestMapping(method = GET)
    public List<Assignment> getAllAssignments(){
        return assignmentService.getAllAssignments();
    }

    @RequestMapping(method = GET, value = "/{id}")
    public ResponseEntity getAssignmentById(@PathVariable int id){
        Assignment a =  assignmentService.getAssignmentById(id);
        if(a == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assignment not found.");
        else
            return ResponseEntity.ok(a);
    }

    @RequestMapping(method = POST)
    public ResponseEntity addAssignment(@Valid @RequestBody Assignment assignment){
        assignmentService.addAssignment(assignment);
        return ResponseEntity.ok().body("New assignment added.");
    }

    @RequestMapping(method = PUT, value = "/{id}")
    public ResponseEntity updateAssignment(@Valid @RequestBody Assignment assignment, @PathVariable int id){
        Assignment a = assignmentService.getAssignmentById(id);
        if(a != null){
            assignment.setId(id);
            assignmentService.updateAssignment(assignment);
            return ResponseEntity.accepted().body("Updated.");
        } else
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assignment not found.");
    }

    @RequestMapping(method = DELETE, value = "/{id}")
    public ResponseEntity deleteAssignment(@PathVariable int id){
        Assignment a = assignmentService.getAssignmentById(id);
        if( a == null)
            return ResponseEntity.notFound().build();
        else {
            assignmentService.deleteAssignment(id);
            return ResponseEntity.ok().body("Assignment deleted");
        }
    }

    @RequestMapping(method = GET, value = "/labs/{labId}")
    public List<Assignment> getAssignmentsByLabId(@PathVariable int labId){
        return assignmentService.getAssignmentsByLabId(labId);
    }
}
