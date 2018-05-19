package consumerContracts;

import model.Student;
import model.request.LoginModel;
import model.request.PasswordModel;
import model.request.StudentRequestModel;

import java.util.List;

public interface IStudentConsumer {
    public List<Student> getAllStudents(LoginModel credentials);
    public int addStudent(StudentRequestModel stud, LoginModel credentials);
    public boolean editStudent(StudentRequestModel stud, int id, LoginModel credentials);
    public boolean deleteStudent(int id, LoginModel credentials);
    public boolean changePassword(String password, LoginModel credentials);
}
