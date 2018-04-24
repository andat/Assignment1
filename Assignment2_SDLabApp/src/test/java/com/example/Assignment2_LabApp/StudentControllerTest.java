package com.example.Assignment2_LabApp;

import com.example.Assignment2_LabApp.controller.StudentController;
import com.example.Assignment2_LabApp.model.entity.Student;
import com.example.Assignment2_LabApp.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)

public class StudentControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ModelMapper modelMapper;

    @MockBean
    StudentService studentService;

    @Test
    public void whenGivenStudents_returnsJsonArray() throws Exception{
        Student s = new Student();
        s.setId(1);
        s.setFullName("student");
        s.setUsername("student");
        s.setEmail("student@gmail.com");
        List<Student> students = Arrays.asList(s);

        given(studentService.getAllStudents()).willReturn(students);

        mvc.perform(get("http://localhost:8080/students")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].username", is(s.getUsername())));
    }
}
