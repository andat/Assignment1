package consumerContracts;

import model.Student;
import model.request.StudentRequestModel;

import java.util.List;

public interface IStudentConsumer {
    public List<Student> getAllStudents();
    public boolean addStudent(StudentRequestModel stud);
    public boolean editStudent(StudentRequestModel stud, int id);
    public boolean deleteStudent(int id);
}
