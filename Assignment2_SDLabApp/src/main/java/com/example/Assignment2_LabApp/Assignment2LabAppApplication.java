package com.example.Assignment2_LabApp;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEmailTools
@SpringBootApplication
public class Assignment2LabAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(Assignment2LabAppApplication.class, args);
	}
}
