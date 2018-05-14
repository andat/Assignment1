package consumerContracts;

import model.Attendance;
import model.Laboratory;
import model.request.AttendanceRequestModel;

import java.util.List;

public interface IAttendanceConsumer {
    public List<Attendance> getAllAttendance();
    public boolean addAttendance(AttendanceRequestModel att);
    public boolean editAttendance(AttendanceRequestModel att, int id);
    public boolean deleteAttendance(int id);
    public List<Attendance> getAttendanceByLabId(int labId);
}
