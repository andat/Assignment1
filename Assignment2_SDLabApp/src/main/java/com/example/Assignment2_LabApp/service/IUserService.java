package com.example.Assignment2_LabApp.service;

import com.example.Assignment2_LabApp.apimodel.Role;
import com.example.Assignment2_LabApp.model.User;

import javax.security.auth.login.LoginException;
import java.util.List;

public interface IUserService {
    public boolean login(String username, String password) throws LoginException;
    public Role getRole(String username);
}
