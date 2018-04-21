package com.example.Assignment2_LabApp.service;

import com.example.Assignment2_LabApp.model.User;

import java.util.List;

public interface IUserService {
    public List<User> getAllUsers();
    public User getUserById(int id);
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(int id);
}
