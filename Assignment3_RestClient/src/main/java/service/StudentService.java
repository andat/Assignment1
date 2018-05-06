package service;

import client.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Student;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import serviceContracts.IStudentService;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StudentService implements IStudentService {
    ObjectMapper objectMapper = new ObjectMapper();

    public List<Student> getAllStudents(){
        String url = "/students";
        List<Student> students = new ArrayList<>();
        try {
            String response = HttpClient.getRequest(url);
            Student[] studentArr = objectMapper.readValue(response, Student[].class);
            students = Arrays.asList(studentArr);
        } catch (IOException e){
            e.printStackTrace();
        }
       return students;
    }

    @Override
    public boolean deleteStudent(int id) {
        String url = "/students/" + id;
        boolean deleted = false;
        try{
            deleted = HttpClient.deleteRequest(url);
        } catch(IOException e){
            e.printStackTrace();
        }
        return deleted;
    }


}
