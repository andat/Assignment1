package consumer;

import client.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Attendance;
import consumerContracts.IAttendanceConsumer;
import model.request.AttendanceRequestModel;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AttendanceConsumer implements IAttendanceConsumer {
    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public List<Attendance> getAllAttendance() {
        String url = "/attendances";
        List<Attendance> attendance = new ArrayList<>();
        try{
            String response = HttpClient.getRequest(url);
            attendance = Arrays.asList(mapper.readValue(response, Attendance[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attendance;
    }

    public boolean addAttendance(AttendanceRequestModel att){
        boolean added = false;
        String url = "/attendances";
        try {
            String body = mapper.writeValueAsString(att);
            added = HttpClient.postRequest(url, new StringEntity(body));
        } catch (IOException e){
            e.printStackTrace();
        }
        return added;
    }

    @Override
    public boolean deleteAttendance(int id) {
       boolean deleted = false;
       String url = "/attendance/" + id;
       try {
           deleted = HttpClient.deleteRequest(url);
       } catch (IOException e) {
           e.printStackTrace();
       }
        return deleted;
    }
}
