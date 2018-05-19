package consumerContracts;

import model.Attendance;
import model.Laboratory;
import model.request.AttendanceRequestModel;
import model.request.LoginModel;

import java.util.List;

public interface IAttendanceConsumer {
    public List<Attendance> getAllAttendance(LoginModel credentials);
    public int addAttendance(AttendanceRequestModel att, LoginModel credentials);
    public boolean editAttendance(AttendanceRequestModel att, int id, LoginModel credentials);
    public boolean deleteAttendance(int id, LoginModel credentials);
    public List<Attendance> getAttendanceByLabId(int labId, LoginModel credentials);
}
