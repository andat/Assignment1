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
import model.request.LoginModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AttendanceController implements Initializable{

    private IAttendanceConsumer attendanceConsumer;
    private IStudentConsumer studentConsumer;
    private ILabConsumer labConsumer;
    private LoginModel credentials;

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

    public AttendanceController(LoginModel credentials){
        this.attendanceConsumer = new AttendanceConsumer();
        this.labConsumer = new LabConsumer();
        this.studentConsumer = new StudentConsumer();
        this.credentials = credentials;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.labComboBox.setItems(FXCollections.observableArrayList(labConsumer.getAllLaboratories(credentials)));
        this.labComboBox1.setItems(FXCollections.observableArrayList(labConsumer.getAllLaboratories(credentials)));
        this.studentComboBox.setItems(FXCollections.observableArrayList(studentConsumer.getAllStudents(credentials)));
        refreshTable();
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
        Attendance a = attendanceTable.getSelectionModel().getSelectedItem();
        if(a != null){
            int id = a.getId();
            attendanceConsumer.editAttendance(att, id, credentials);
            refreshTable();
        }
    }

    @FXML
    public void deleteBtnClicked(){
        Attendance selectedAttendance = (Attendance) attendanceTable.getSelectionModel().getSelectedItem();
        if(selectedAttendance != null){
            attendanceConsumer.deleteAttendance(selectedAttendance.getId(), credentials);
        }
        refreshTable();
    }

    @FXML
    public void addBtnClicked(){
        AttendanceRequestModel att = getAttendanceFromFields();
        if(att != null) {
            if (attendanceConsumer.addAttendance(att, credentials) == 400)
                AlertBox.display("Invalid operation", "A person can have only one attendance per laboratory.");
                refreshTable();
        }
        presentRadioBtn.setSelected(false);
    }

    @FXML
    public void viewAttendanceByLab(){
        if(labComboBox1.getValue() != null){
            int labId = labComboBox1.getValue().getId();
            List<Attendance> list = this.attendanceConsumer.getAttendanceByLabId(labId, credentials);
            attendanceTable.setItems(FXCollections.observableArrayList(list));
        } else {
            AlertBox.display("No laboratory selected", "Please select a laboratory.");
        }

    }

    @FXML
    public void updateLabComboBox(){
        this.labComboBox.setItems(FXCollections.observableArrayList(labConsumer.getAllLaboratories(credentials)));
        this.labComboBox1.setItems(FXCollections.observableArrayList(labConsumer.getAllLaboratories(credentials)));
    }

    @FXML
    public void updateStudComboBox(){
        this.studentComboBox.setItems(FXCollections.observableArrayList(studentConsumer.getAllStudents(credentials)));
    }

    private void refreshTable(){
        attendanceTable.setItems(FXCollections.observableArrayList(attendanceConsumer.getAllAttendance(credentials)));
    }

    private AttendanceRequestModel getAttendanceFromFields(){
        Student s = null;
        if(studentComboBox.getValue() != null)
            s = studentComboBox.getValue();
        Laboratory l = null;
        if(labComboBox.getValue() != null)
            l = labComboBox.getValue();
        boolean present = presentRadioBtn.isSelected();
        if( s!= null && l != null)
            return new AttendanceRequestModel(l.getId(), s.getId(), present);
        else{
            AlertBox.display("Empty fields", "Please select a student and a laboratory.");
            return null;
        }
    }

    public void setCredentials(LoginModel credentials){
        this.credentials = credentials;
    }
}
