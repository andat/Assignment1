package com.example.Assignment2_LabApp.controller;

import com.example.Assignment2_LabApp.model.User;
import com.example.Assignment2_LabApp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(method = GET)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(method = GET, value = "/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @RequestMapping(method = POST)
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @RequestMapping(method = PUT, value = "/{id}")
    public void updateUser(@RequestBody User user){
        //TODO check if user exists
        userService.updateUser(user);
    }

    @RequestMapping(method = DELETE, value = "/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }
}
