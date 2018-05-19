package com.example.Assignment2_LabApp.util;

import com.example.Assignment2_LabApp.model.entity.Student;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomEmailService {
    @Autowired
    private EmailService emailService;
    public void sendTokenEmail(String address, String name, String token) throws UnsupportedEncodingException {
        List<InternetAddress> addresses = new ArrayList<>();
        addresses.add(new InternetAddress(address, name));
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress("dt266226662@gmail.com",
                        "SD Lab App Mail Service"))
                .to(addresses)
                .subject("Your token for SD Laboratory application")
                .body("An account for this email address has been created.\n" +
                        "To register, please use this token: " + token)
                .encoding("UTF-8").build();

        emailService.send(email);
    }

    public void sendAssignmentAddedEmail(List<Student> students)throws UnsupportedEncodingException {
        List<InternetAddress> addresses = students.stream().map(s -> {
                                try {
                                    return new InternetAddress(s.getEmail(), s.getFullName());
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                return null;
                            })
                            .collect(Collectors.toList());
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress("dt266226662@gmail.com",
                        "SD Lab App Mail Service"))
                .to(addresses)
                .subject("New assignment added")
                .body("Dear user,\n\n" +
                        "This email is to inform you that a new assignment for the SD laboratory class was added.")
                .encoding("UTF-8").build();
        emailService.send(email);
    }
}
