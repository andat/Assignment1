package com.example.Assignment2_LabApp.repository;

import com.example.Assignment2_LabApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User getUserByUsername(String username);
}
