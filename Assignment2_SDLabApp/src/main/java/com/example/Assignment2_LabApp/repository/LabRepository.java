package com.example.Assignment2_LabApp.repository;

import com.example.Assignment2_LabApp.model.entity.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabRepository extends JpaRepository<Laboratory, Integer> {
    public List<Laboratory> findAllByCurriculaContainsOrDescriptionContains(String currKeyword, String descKeyword);
}
