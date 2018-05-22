package controller;

import consumer.AttendanceConsumer;
import consumer.LabConsumer;
import consumer.StudentConsumer;
import consumerContracts.IAttendanceConsumer;
import consumerContracts.ILabConsumer;
import consumerContracts.IStudentConsumer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import model.Attendance;
import model.Laboratory;
import model.Student;
import model.request.AttendanceRequestModel;
import model.request.LoginModel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AttendanceStudentController implements Initializable{
    private IAttendanceConsumer attendanceConsumer;
    private LoginModel credentials;

    @FXML
    TableView<Attendance> attendanceTable;

    public AttendanceStudentController(LoginModel credentials){
        this.attendanceConsumer = new AttendanceConsumer();
        this.credentials = credentials;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable();
    }

    @FXML
    public void viewBtnClicked(){
        refreshTable();
    }



    private void refreshTable(){
        attendanceTable.setItems(FXCollections.observableArrayList(attendanceConsumer.getAttendanceByUsername(credentials)));
    }
}
