package com.example.Assignment2_LabApp.service;

import com.example.Assignment2_LabApp.model.Laboratory;
import com.example.Assignment2_LabApp.model.Student;
import com.example.Assignment2_LabApp.repository.StudentRepository;
//import com.example.Assignment2_LabApp.util.PasswordEncoder;
import com.example.Assignment2_LabApp.util.PasswordEncryptionUtil;
import com.example.Assignment2_LabApp.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService{
    
    @Autowired 
    private StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(int id){
        Optional<Student> result = studentRepository.findById(id);
        return result.orElse(null);
    }

    public void addStudent(Student student, String token){
        student.setPassword(PasswordEncryptionUtil.encryptPasswordSHA256(token));
        studentRepository.save(student);
    }

    public void updateStudent(Student student){
        studentRepository.save(student);
    }

    public void deleteStudent(int id){
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent())
            studentRepository.delete(student.get());
    }

    @Override
    public void changePassword(Student student, String password) {
       student.setPassword(PasswordEncryptionUtil.encryptPasswordSHA256(password));
       studentRepository.save(student);
    }
}
