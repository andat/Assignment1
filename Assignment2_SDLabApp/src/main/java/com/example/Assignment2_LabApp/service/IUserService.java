package com.example.Assignment2_LabApp.service;

import com.example.Assignment2_LabApp.model.User;

import java.util.List;

//TODO update user methods
public interface IUserService {
    public List<User> getAllUsers();
    public User getUserById(int id);
    public User getUserByUsername(String username);
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(int id);
    public boolean login(String username, String password);
}
