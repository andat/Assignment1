package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Student;
import service.StudentService;
import serviceContracts.IStudentService;

import java.util.List;

public class StudentTableController {
    private IStudentService studentService;

    @FXML
    TableView<Student> studentTable;

    @FXML
    TableColumn<Student, String> usernameCol;

    @FXML
    TableColumn<Student, String> nameCol;

    @FXML
    TableColumn<Student, String> emailCol;

    @FXML
    TableColumn<Student, String> groupCol;

    @FXML
    TableColumn<Student, String> hobbyCol;

    public StudentTableController(){
        this.studentService = new StudentService();
    }

    @FXML
    public void viewBtnClicked(){
        refreshTable();
    }

    @FXML
    public void editBtnClicked(){

    }

    @FXML
    public void addBtnClicked(){

    }

    @FXML
    public void deleteBtnClicked(){
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if(selectedStudent != null){
            studentService.deleteStudent(selectedStudent.getId());
        }
        refreshTable();
    }

    private void refreshTable(){
        studentTable.setItems(FXCollections.observableArrayList(studentService.getAllStudents()));
    }

    @FXML
    public void editUsernameCell(){

    }

    @FXML
    public void editFullNameCell(){

    }

    @FXML
    public void editEmailCell(){

    }

    @FXML
    public void editGroupCell(){

    }

    @FXML
    public void editHobbyCell(){

    }
}
