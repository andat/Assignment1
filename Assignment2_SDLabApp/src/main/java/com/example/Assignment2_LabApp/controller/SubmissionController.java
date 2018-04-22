package com.example.Assignment2_LabApp.controller;

import com.example.Assignment2_LabApp.model.Submission;
import com.example.Assignment2_LabApp.service.ISubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    @Autowired
    private ISubmissionService submissionService;

    @RequestMapping(method = GET)
    public List<Submission> getAllSubmissions(){
        return submissionService.getAllSubmissions();
    }

    @RequestMapping(method = GET, value = "/{id}")
    public Submission getSubmissionById(@PathVariable int id){
        return submissionService.getSubmissionById(id);
    }

    @RequestMapping(method = POST)
    public void addSubmission(@RequestBody Submission submission){
        submissionService.addSubmission(submission);
    }

    @RequestMapping(method = PUT, value = "/{id}")
    public void updateSubmission(@RequestBody Submission submission, @PathVariable int id){
        //TODO check if user exists
        submissionService.updateSubmission(submission);
    }

    @RequestMapping(method = DELETE, value = "/{id}")
    public void deleteSubmission(@PathVariable int id){
        submissionService.deleteSubmission(id);
    }

    @RequestMapping(method = GET, value = "/assignments/{assignmentId}")
    public List<Submission> getSubmissionsByAssignmentId(@PathVariable int assignmentId){
        return submissionService.getSubmissionsByAssignmentId(assignmentId);
    }
}
