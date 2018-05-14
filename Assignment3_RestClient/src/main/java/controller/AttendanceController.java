package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.Attendance;
import consumer.AttendanceConsumer;
import consumerContracts.IAttendanceConsumer;

public class AttendanceController {

    private IAttendanceConsumer attendanceService;

    @FXML
    TableView attendanceTable;

    public AttendanceController(){
        this.attendanceService = new AttendanceConsumer();
    }

    @FXML
    public void viewBtnClicked(){
        refreshTable();
    }

    @FXML
    public void editBtnClicked(){

    }

    @FXML
    public void deleteBtnClicked(){
        Attendance selectedAttendance = (Attendance) attendanceTable.getSelectionModel().getSelectedItem();
        if(selectedAttendance != null){
            attendanceService.deleteAttendance(selectedAttendance.getId());
        }
        refreshTable();
    }

    @FXML
    public void addBtnClicked(){

    }

    private void refreshTable(){
        attendanceTable.setItems(FXCollections.observableArrayList(attendanceService.getAllAttendance()));
    }
}
