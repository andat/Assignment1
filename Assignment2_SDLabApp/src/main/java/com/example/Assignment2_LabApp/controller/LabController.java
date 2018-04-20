package com.example.Assignment2_LabApp.controller;

import com.example.Assignment2_LabApp.model.Laboratory;
import com.example.Assignment2_LabApp.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LabController {

    @Autowired
    private LabService labService;

    @RequestMapping("/laboratory")
    public List<Laboratory> getAllLaboratories(){
        return labService.getAllLaboratories();
    }

    @RequestMapping("laboratory/{id}")
    public Laboratory getLabById(@PathVariable int id){
        return labService.getLabById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/laboratory")
    public void addLaboratory(@RequestBody Laboratory laboratory){
        labService.addLaboratory(laboratory);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/laboratory/{id}")
    public void updateLaboratory(@RequestBody Laboratory laboratory, @PathVariable int id){
        labService.updateLaboratory(laboratory, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/laboratory/{id}")
    public void deleteLaboratory(@PathVariable int id){
        labService.deleteLaboratory(id);
    }
}
