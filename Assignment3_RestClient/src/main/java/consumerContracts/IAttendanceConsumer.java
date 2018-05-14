package consumerContracts;

import model.Attendance;

import java.util.List;

public interface IAttendanceConsumer {
    public List<Attendance> getAllAttendance();
    public boolean deleteAttendance(int id);
}
