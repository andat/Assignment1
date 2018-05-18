package com.example.Assignment2_LabApp.service;

import com.example.Assignment2_LabApp.model.login.Role;
import com.example.Assignment2_LabApp.model.entity.User;
import com.example.Assignment2_LabApp.repository.UserRepository;
import com.example.Assignment2_LabApp.util.PasswordEncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepository;

    public Role login(String username, String password) throws LoginException {
        User user = userRepository.getUserByUsername(username);
        if(user == null)
            throw new LoginException("Invalid username.");
        else
            if(PasswordEncryptionUtil.validatePassword(password, user.getPassword()))
                return getRole(username);
            else
                throw new LoginException("Wrong password.");
    }

    private Role getRole(String username) {
        User user = userRepository.getUserByUsername(username);
        if(user.isTeacher())
            return Role.TEACHER;
        else
            return Role.STUDENT;
    }
}
