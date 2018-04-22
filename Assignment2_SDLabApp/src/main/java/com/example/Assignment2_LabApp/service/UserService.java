package com.example.Assignment2_LabApp.service;

import com.example.Assignment2_LabApp.model.User;
import com.example.Assignment2_LabApp.repository.UserRepository;
import com.example.Assignment2_LabApp.util.PasswordEncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        Optional<User> result = userRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent())
            userRepository.delete(user.get());
    }

    public boolean login(String username, String password){
        User user = userRepository.getUserByUsername(username);
        return PasswordEncryptionUtil.validatePassword(password, user.getPassword());
    }
}
