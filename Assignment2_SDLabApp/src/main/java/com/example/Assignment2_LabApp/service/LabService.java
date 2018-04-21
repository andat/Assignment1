package com.example.Assignment2_LabApp.service;

import com.example.Assignment2_LabApp.model.Laboratory;
import com.example.Assignment2_LabApp.repository.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabService implements ILabService{

    @Autowired
    private LabRepository labRepository;

    public List<Laboratory> getAllLaboratories(){
        return labRepository.findAll();
    }

    public Laboratory getLabById(int id){
        Optional<Laboratory> result = labRepository.findById(id);
        return result.orElse(null);
    }

    public void addLaboratory(Laboratory lab){
        labRepository.save(lab);
    }

    public void updateLaboratory(Laboratory lab){
        labRepository.save(lab);
    }

    public void deleteLaboratory(int id){
        Optional<Laboratory> lab = labRepository.findById(id);
        if(lab.isPresent())
            labRepository.delete(lab.get());
    }

    @Override
    public List<Laboratory> filterByKeyword(String keyword) {
        return labRepository.findAllByCurriculaContainsOrDescriptionContains(keyword, keyword);
    }

}
