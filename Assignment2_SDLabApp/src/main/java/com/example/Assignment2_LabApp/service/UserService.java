package com.example.Assignment2_LabApp.service;

import com.example.Assignment2_LabApp.apimodel.Role;
import com.example.Assignment2_LabApp.model.User;
import com.example.Assignment2_LabApp.repository.UserRepository;
import com.example.Assignment2_LabApp.util.PasswordEncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepository;

    public boolean login(String username, String password) throws LoginException {
        User user = userRepository.getUserByUsername(username);
        if(user == null)
            throw new LoginException("Invalid username.");
        else
            return PasswordEncryptionUtil.validatePassword(password, user.getPassword());
    }

    @Override
    public Role getRole(String username) {
        User user = userRepository.getUserByUsername(username);
        if(user.isTeacher())
            return Role.TEACHER;
        else
            return Role.STUDENT;
    }
}
