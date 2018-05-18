package com.example.Assignment2_LabApp.service;

import com.example.Assignment2_LabApp.model.login.Role;

import javax.security.auth.login.LoginException;

public interface IUserService {
    public Role login(String username, String password) throws LoginException;
}
