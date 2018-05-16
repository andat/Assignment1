package com.example.Assignment2_LabApp.controller;

import com.example.Assignment2_LabApp.model.login.LoginModel;
import com.example.Assignment2_LabApp.model.login.Role;
import com.example.Assignment2_LabApp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.security.auth.login.LoginException;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private IUserService userService;

    @RequestMapping(method = POST)
    public ResponseEntity login(@Validated @RequestBody LoginModel l){
        System.out.println(l);
        try {
            if(userService.login(l.getUsername(), l.getPassword())){
                Role role = userService.getRole(l.getUsername());
                return ResponseEntity.ok(role);
            }
            else
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        } catch (LoginException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
