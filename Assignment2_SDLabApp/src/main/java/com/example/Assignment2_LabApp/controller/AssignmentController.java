package com.example.Assignment2_LabApp.controller;

import com.example.Assignment2_LabApp.model.Assignment;
import com.example.Assignment2_LabApp.service.IAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Assignment getAssignmentById(@PathVariable int id){
        return assignmentService.getAssignmentById(id);
    }

    @RequestMapping(method = POST)
    public void addAssignment(@RequestBody Assignment assignment){
        assignmentService.addAssignment(assignment);
    }

    @RequestMapping(method = PUT, value = "/{id}")
    public void updateAssignment(@RequestBody Assignment assignment, @PathVariable int id){
        //TODO check if assignment exists
        assignmentService.updateAssignment(assignment);
    }

    @RequestMapping(method = DELETE, value = "/{id}")
    public void deleteAssignment(@PathVariable int id){
        assignmentService.deleteAssignment(id);
    }

}
