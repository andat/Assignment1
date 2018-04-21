package com.example.Assignment2_LabApp.service;

import com.example.Assignment2_LabApp.model.Laboratory;

import java.util.List;

public interface ILabService {
    public List<Laboratory> getAllLaboratories();
    public Laboratory getLabById(int id);
    public void addLaboratory(Laboratory lab);
    public void updateLaboratory(Laboratory lab);
    public void deleteLaboratory(int id);
    public List<Laboratory> filterByKeyword(String keyword);
}
