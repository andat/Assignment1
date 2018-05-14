package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Student;
import consumer.StudentConsumer;
import consumerContracts.IStudentConsumer;
import model.request.StudentRequestModel;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentTableController implements Initializable{

    private IStudentConsumer studentConsumer;

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

    @FXML
    ComboBox groupComboBox;

    @FXML
    TextField emailField;

    @FXML
    TextField nameField;

    @FXML
    TextField userField;

    @FXML
    TextField hobbyField;

    public StudentTableController(){
        this.studentConsumer = new StudentConsumer();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> groups = FXCollections.observableArrayList("30431", "30432", "30433", "30434");
        this.groupComboBox.setItems(groups);
    }

    @FXML
    public void viewBtnClicked(){
        refreshTable();
    }

    @FXML
    public void editBtnClicked(){
        StudentRequestModel stud = getStudentFromFields();
        int id = studentTable.getSelectionModel().getSelectedItem().getId();
        studentConsumer.editStudent(stud, id);
        //System.out.println(id);
        refreshTable();
        clearFields();
    }

    @FXML
    public void addBtnClicked(){
        studentConsumer.addStudent(getStudentFromFields());
        refreshTable();
        clearFields();
    }

    private void clearFields(){
        nameField.clear();
        emailField.clear();
        userField.clear();
        hobbyField.clear();
    }

    private StudentRequestModel getStudentFromFields(){
        String name = nameField.getText();
        String email = emailField.getText();
        String username = userField.getText();
        String hobby = hobbyField.getText();
        String group = (String) groupComboBox.getValue();

        //TODO validate fields
        return new StudentRequestModel(username, name, email, group, hobby);
    }

    @FXML
    public void rowSelected() {
        Student selected = studentTable.getSelectionModel().getSelectedItem();

        if (selected != null) {
            String name = selected.getFullName();
            String group = selected.getGroupName();
            String username = selected.getUsername();
            String email = selected.getEmail();
            String hobby = selected.getHobby();

            this.nameField.setText(name);
            this.groupComboBox.setValue(group);
            this.userField.setText(username);
            this.emailField.setText(email);
            this.hobbyField.setText(hobby);
        }
    }

    @FXML
    public void deleteBtnClicked(){
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if(selectedStudent != null){
            studentConsumer.deleteStudent(selectedStudent.getId());
        }
        refreshTable();
    }

    private void refreshTable(){
        studentTable.setItems(FXCollections.observableArrayList(studentConsumer.getAllStudents()));
    }
}
