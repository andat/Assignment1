package com.example.Assignment2_LabApp.controller;

import com.example.Assignment2_LabApp.model.request.SubmissionRequestModel;
import com.example.Assignment2_LabApp.model.response.SubmissionResponseModel;
import com.example.Assignment2_LabApp.model.entity.Submission;
import com.example.Assignment2_LabApp.service.IAssignmentService;
import com.example.Assignment2_LabApp.service.ISubmissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {
    private static int MAX_SUBMISSION_NO = 3;

    @Autowired
    private ISubmissionService submissionService;

    @Autowired
    private IAssignmentService assignmentService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = GET)
    public List<SubmissionResponseModel> getAllSubmissions(){
        return submissionService.getAllSubmissions().stream()
                                                    .map(s -> modelMapper.map(s, SubmissionResponseModel.class))
                                                    .collect(Collectors.toList());
    }

    @RequestMapping(method = GET, value = "/{id}")
    public ResponseEntity getSubmissionById(@PathVariable int id){
        Submission s = submissionService.getSubmissionById(id);
        if(s == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Submission not found");
        else
            return ResponseEntity.ok().body(modelMapper.map(s, SubmissionResponseModel.class));
    }

    @RequestMapping(method = POST)
    public ResponseEntity addSubmission(@RequestBody SubmissionRequestModel submission){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Submission sub = modelMapper.map(submission, Submission.class);
        Date deadline = assignmentService.getAssignmentById(sub.getAssignment().getId()).getDeadline();

        if(submissionService.checkValidSubmission(sub, deadline)){
            //check if new submission can be added
            if(submissionService.checkMaxNoOfSubmissionsReached(sub, MAX_SUBMISSION_NO))
                return ResponseEntity.badRequest().body("Maximum number of submissions reached.");
            else{
                sub.setDate(new Date(System.currentTimeMillis()));
                submissionService.addSubmission(sub);
                return ResponseEntity.ok().body("New submission added.");
            }
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Submission is not valid. Assignment deadline passed.");

    }

    @RequestMapping(method = PUT, value = "/{id}")
    public ResponseEntity updateSubmission(@RequestBody SubmissionRequestModel submission, @PathVariable int id){
        Submission oldSubmission = submissionService.getSubmissionById(id);
        if(oldSubmission != null){
            Submission s = modelMapper.map(submission, Submission.class);
            s.setId(id);
            s.setDate(oldSubmission.getDate());
            submissionService.updateSubmission(s);
            return  ResponseEntity.ok().body("Submission updated.");
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Submission not found");

    }

    @RequestMapping(method = PUT, value ="/{id}/{grade}")
    public ResponseEntity gradeSubmission(@PathVariable int id, @PathVariable @Min(1) @Max(10) int grade){
        Submission sub = submissionService.getSubmissionById(id);
        if(sub != null){
            submissionService.gradeSubmission(sub, grade);
            return ResponseEntity.ok().body("Submission graded.");
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Submission not found");
    }

    @RequestMapping(method = DELETE, value = "/{id}")
    public ResponseEntity deleteSubmission(@PathVariable int id){
        if(submissionService.getSubmissionById(id)!= null){
            submissionService.deleteSubmission(id);
            return ResponseEntity.ok().body("Submission deleted.");
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Submission not found");
    }

    @RequestMapping(method = GET, value = "/assignments/{assignmentId}")
    public List<SubmissionResponseModel> getSubmissionsByAssignmentId(@PathVariable int assignmentId){
        return submissionService.getSubmissionsByAssignmentId(assignmentId).stream()
                                                                           .map(s -> modelMapper.map(s, SubmissionResponseModel.class))
                                                                           .collect(Collectors.toList());
    }

    @RequestMapping(method = GET, value = "/students/{username}")
    public List<SubmissionResponseModel> getSubmissionsByAssignmentId(@PathVariable String username){
        return submissionService.getSubmissionsByUsername(username).stream()
                .map(s -> modelMapper.map(s, SubmissionResponseModel.class))
                .collect(Collectors.toList());
    }
}
