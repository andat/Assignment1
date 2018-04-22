package com.example.Assignment2_LabApp.controller;

import com.example.Assignment2_LabApp.apimodel.AssignmentRequestModel;
import com.example.Assignment2_LabApp.apimodel.AssignmentResponseModel;
import com.example.Assignment2_LabApp.model.Assignment;
import com.example.Assignment2_LabApp.service.IAssignmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    @Autowired
    private IAssignmentService assignmentService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = GET)
    public List<AssignmentResponseModel> getAllAssignments(){
        return assignmentService.getAllAssignments().stream()
                                                    .map(a -> modelMapper.map(a, AssignmentResponseModel.class))
                                                    .collect(Collectors.toList());
    }

    @RequestMapping(method = GET, value = "/{id}")
    public ResponseEntity getAssignmentById(@PathVariable int id){
        Assignment a =  assignmentService.getAssignmentById(id);
        if(a == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assignment not found.");
        else
            return ResponseEntity.ok(modelMapper.map(a, AssignmentResponseModel.class));
    }

    @RequestMapping(method = POST)
    public ResponseEntity addAssignment(@Valid @RequestBody AssignmentRequestModel a){
        Assignment assignment = modelMapper.map(a, Assignment.class);
        assignmentService.addAssignment(assignment);
        return ResponseEntity.ok().body("New assignment added.");
    }

    @RequestMapping(method = PUT, value = "/{id}")
    public ResponseEntity updateAssignment(@Valid @RequestBody AssignmentRequestModel assignment, @PathVariable int id){
        Assignment a = assignmentService.getAssignmentById(id);
        if(a != null){
            Assignment newAssignment = modelMapper.map(assignment, Assignment.class);
            newAssignment.setId(id);
            assignmentService.updateAssignment(newAssignment);
            return ResponseEntity.accepted().body("Assignment updated.");
        } else
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assignment not found.");
    }

    //TODO fix delete
    @RequestMapping(method = DELETE, value = "/{id}")
    public ResponseEntity deleteAssignment(@PathVariable int id){
        if(assignmentService.getAssignmentById(id) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assignment not found.");
        else {
            assignmentService.deleteAssignment(id);
            return ResponseEntity.ok().body("Assignment deleted");
        }
    }

    @RequestMapping(method = GET, value = "/labs/{labId}")
    public List<AssignmentResponseModel> getAssignmentsByLabId(@PathVariable int labId){
        return assignmentService.getAssignmentsByLabId(labId).stream()
                                                             .map(a -> modelMapper.map(a, AssignmentResponseModel.class))
                                                             .collect(Collectors.toList());
    }
}
