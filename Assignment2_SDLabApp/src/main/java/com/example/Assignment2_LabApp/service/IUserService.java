package com.example.Assignment2_LabApp.service;

import com.example.Assignment2_LabApp.model.Role;

import javax.security.auth.login.LoginException;

public interface IUserService {
    public boolean login(String username, String password) throws LoginException;
    public Role getRole(String username);
}
