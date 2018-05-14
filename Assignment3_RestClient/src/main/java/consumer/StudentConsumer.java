package consumer;

import client.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Student;
import consumerContracts.IStudentConsumer;
import model.request.StudentRequestModel;
import org.apache.http.entity.StringEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentConsumer implements IStudentConsumer {
    private ObjectMapper objectMapper = new ObjectMapper();

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

    public boolean addStudent(StudentRequestModel stud){
        boolean added = false;
        String url = "/students";
        try {
            String body = objectMapper.writeValueAsString(stud);
            added = HttpClient.postRequest(url, new StringEntity(body));
        } catch (IOException e){
            e.printStackTrace();
        }
        return added;
    }

    public boolean editStudent(StudentRequestModel stud, int id){
        boolean edited = false;
        String url = "/students/" + id;
        try{
            String body = objectMapper.writeValueAsString(stud);
            edited = HttpClient.putRequest(url, new StringEntity(body));
        } catch (IOException e){
            e.printStackTrace();
        }
        return edited;
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
