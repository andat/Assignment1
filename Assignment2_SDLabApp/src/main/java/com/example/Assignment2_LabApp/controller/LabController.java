package com.example.Assignment2_LabApp.controller;

import com.example.Assignment2_LabApp.apimodel.LabRequestModel;
import com.example.Assignment2_LabApp.apimodel.LabResponseModel;
import com.example.Assignment2_LabApp.model.Laboratory;
import com.example.Assignment2_LabApp.service.ILabService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/laboratories")
public class LabController {

    @Autowired
    private ILabService labService;

    private ModelMapper modelMapper = new ModelMapper();

    @RequestMapping(method = GET)
    public List<LabResponseModel> getLaboratories(@RequestParam (value = "keyword", required = false) String keyword){
        if(keyword == null)
            return labService.getAllLaboratories().stream()
                                                  .map(lab -> modelMapper.map(lab, LabResponseModel.class))
                                                  .collect(Collectors.toList());
        else
            return labService.filterByKeyword(keyword).stream()
                                                      .map(lab -> modelMapper.map(lab, LabResponseModel.class))
                                                      .collect(Collectors.toList());
    }

    @RequestMapping(method = GET, value = "/{id}")
    public ResponseEntity getLabById(@PathVariable Integer id){
        Laboratory lab = labService.getLabById(id);
        if(lab != null){
            return ResponseEntity.ok(modelMapper.map(lab, LabResponseModel.class));
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Laboratory not found.");
    }

    @RequestMapping(method = POST)
    public ResponseEntity addLaboratory(@RequestBody LabRequestModel lab){
        Laboratory laboratory = modelMapper.map(lab, Laboratory.class);
        labService.addLaboratory(laboratory);
        return ResponseEntity.ok("New laboratory added");
    }

    @RequestMapping(method = PUT, value = "/{id}")
    public ResponseEntity updateLaboratory(@Validated @RequestBody LabRequestModel lab, @PathVariable int id){
        if(labService.getLabById(id)!= null){
            Laboratory laboratory = modelMapper.map(lab, Laboratory.class);
            laboratory.setId(id);
            labService.updateLaboratory(laboratory);
            return ResponseEntity.ok("Laboratory updated");
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Laboratory not found.");

    }

    @RequestMapping(method = DELETE, value = "/{id}")
    public ResponseEntity deleteLaboratory(@PathVariable int id){
        if(labService.getLabById(id) != null){
            labService.deleteLaboratory(id);
            return ResponseEntity.ok("Laboratory deleted.");
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Laboratory not found.");
    }
}
