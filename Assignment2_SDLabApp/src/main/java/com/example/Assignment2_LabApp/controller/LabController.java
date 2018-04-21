package com.example.Assignment2_LabApp.controller;

import com.example.Assignment2_LabApp.model.Laboratory;
import com.example.Assignment2_LabApp.service.ILabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/laboratories")
public class LabController {

    @Autowired
    private ILabService labService;

    @RequestMapping(method = GET)
    public List<Laboratory> getLaboratories(@RequestParam (value = "keyword", required = false) String keyword){
        if(keyword == null)
            return labService.getAllLaboratories();
        else
            return labService.filterByKeyword(keyword);
    }

    @RequestMapping(method = GET, value = "/{id}")
    public Laboratory getLabById(@PathVariable Integer id){
        return labService.getLabById(id);
    }

    @RequestMapping(method = POST)
    public void addLaboratory(@RequestBody Laboratory laboratory){
        labService.addLaboratory(laboratory);
    }

    @RequestMapping(method = PUT, value = "/{id}")
    public void updateLaboratory(@RequestBody Laboratory laboratory, @PathVariable int id){
        //TODO check if user exists
        labService.updateLaboratory(laboratory);
    }

    @RequestMapping(method = DELETE, value = "/{id}")
    public void deleteLaboratory(@PathVariable int id){
        labService.deleteLaboratory(id);
    }
}
