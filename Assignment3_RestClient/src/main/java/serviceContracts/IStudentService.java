package serviceContracts;

import model.Student;

import java.util.List;

public interface IStudentService {
    public List<Student> getAllStudents();
    public boolean deleteStudent(int id);
}
