package com.example.Assignment2_LabApp.controller;

import com.example.Assignment2_LabApp.model.User;
import com.example.Assignment2_LabApp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private IUserService userService;

    @RequestMapping(method = POST)
    public ResponseEntity login(@RequestBody User user){
        if(userService.login(user.getUsername(), user.getPassword()))
            return ResponseEntity.ok("Login successful.");
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
    }


}
