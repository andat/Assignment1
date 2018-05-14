package controller;

import consumer.LabConsumer;
import consumer.StudentConsumer;
import consumerContracts.ILabConsumer;
import consumerContracts.IStudentConsumer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import model.Attendance;
import consumer.AttendanceConsumer;
import consumerContracts.IAttendanceConsumer;
import model.Laboratory;
import model.Student;
import model.request.AttendanceRequestModel;
import model.request.LabRequestModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AttendanceController implements Initializable{

    private IAttendanceConsumer attendanceConsumer;
    private IStudentConsumer studentConsumer;
    private ILabConsumer labConsumer;

    @FXML
    TableView<Attendance> attendanceTable;

    @FXML
    ComboBox<Laboratory> labComboBox;

    @FXML
    ComboBox<Laboratory> labComboBox1;

    @FXML
    ComboBox<Student> studentComboBox;

    @FXML
    RadioButton presentRadioBtn;

    public AttendanceController(){
        this.attendanceConsumer = new AttendanceConsumer();
        this.labConsumer = new LabConsumer();
        this.studentConsumer = new StudentConsumer();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.labComboBox.setItems(FXCollections.observableArrayList(labConsumer.getAllLaboratories()));
        this.labComboBox1.setItems(FXCollections.observableArrayList(labConsumer.getAllLaboratories()));
        this.studentComboBox.setItems(FXCollections.observableArrayList(studentConsumer.getAllStudents()));
    }

    @FXML
    public void viewBtnClicked(){
        refreshTable();
    }

    @FXML
    public void rowSelected(){
        Attendance a = this.attendanceTable.getSelectionModel().getSelectedItem();
        if(a != null) {
            List<Laboratory> labs = labComboBox.getItems().filtered(l -> l.getNumber() == a.getLaboratoryNumber());
            List<Student> students = studentComboBox.getItems().filtered(s -> s.getUsername().equals(a.getStudentUsername()));
            labComboBox.setValue(labs.get(0));
            studentComboBox.setValue(students.get(0));
            presentRadioBtn.setSelected(a.isPresent());
        }
    }

    @FXML
    public void editBtnClicked(){
        AttendanceRequestModel att = getAttendanceFromFields();
        int id = attendanceTable.getSelectionModel().getSelectedItem().getId();
        attendanceConsumer.editAttendance(att, id);
        refreshTable();
    }

    @FXML
    public void deleteBtnClicked(){
        Attendance selectedAttendance = (Attendance) attendanceTable.getSelectionModel().getSelectedItem();
        if(selectedAttendance != null){
            attendanceConsumer.deleteAttendance(selectedAttendance.getId());
        }
        refreshTable();
    }

    @FXML
    public void addBtnClicked(){
        AttendanceRequestModel att = getAttendanceFromFields();
        attendanceConsumer.addAttendance(att);
        refreshTable();
        presentRadioBtn.setSelected(false);
    }

    @FXML
    public void viewAttendanceByLab(){
        int labId = labComboBox1.getValue().getId();
        List<Attendance> list = this.attendanceConsumer.getAttendanceByLabId(labId);
        attendanceTable.setItems(FXCollections.observableArrayList(list));
    }

    @FXML
    public void updateLabComboBox(){
        this.labComboBox.setItems(FXCollections.observableArrayList(labConsumer.getAllLaboratories()));
        this.labComboBox1.setItems(FXCollections.observableArrayList(labConsumer.getAllLaboratories()));
    }

    @FXML
    public void updateStudComboBox(){
        this.studentComboBox.setItems(FXCollections.observableArrayList(studentConsumer.getAllStudents()));
    }

    private void refreshTable(){
        attendanceTable.setItems(FXCollections.observableArrayList(attendanceConsumer.getAllAttendance()));
    }

    private AttendanceRequestModel getAttendanceFromFields(){
        Student s = studentComboBox.getValue();
        Laboratory l = labComboBox.getValue();
        boolean present = presentRadioBtn.isSelected();
        return new AttendanceRequestModel(l.getId(), s.getId(), present);
    }

}
