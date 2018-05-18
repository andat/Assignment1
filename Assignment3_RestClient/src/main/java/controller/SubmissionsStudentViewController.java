package controller;

import consumer.AssignmentConsumer;
import consumer.StudentConsumer;
import consumer.SubmissionConsumer;
import consumerContracts.IAssignmentConsumer;
import consumerContracts.IStudentConsumer;
import consumerContracts.ISubmissionConsumer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import model.Assignment;
import model.Student;
import model.Submission;
import model.request.LoginModel;
import model.request.SubmissionRequestModel;
import sun.rmi.runtime.Log;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SubmissionsStudentViewController implements Initializable{
    private ISubmissionConsumer submissionConsumer;
    private IAssignmentConsumer assignmentConsumer;
    private IStudentConsumer studentConsumer;
    private LoginModel credentials;
    private int studentId;

    @FXML
    TableView<Submission> submissionTable;

    @FXML
    ComboBox assignmentComboBox;

    @FXML
    TextArea descriptionArea;

    public SubmissionsStudentViewController(LoginModel credentials){
        this.submissionConsumer = new SubmissionConsumer();
        this.assignmentConsumer = new AssignmentConsumer();
        this.studentConsumer = new StudentConsumer();
        this.credentials = credentials;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(credentials != null) {
            this.assignmentComboBox.setItems(FXCollections.observableArrayList(assignmentConsumer.getAllAssignments(credentials)));
            refreshTable();
            //Student stud = studentConsumer.getAllStudents(credentials).stream().filter(s -> s.getUsername().equals(credentials.getUsername())).findFirst().orElse(null);
            //if (stud != null) {
//                studentId = stud.getId();
//            }
        }
    }

    @FXML
    public void viewBtnClicked(){
        refreshTable();
    }

    @FXML
    public void deleteBtnClicked(){
        Submission s = (Submission) submissionTable.getSelectionModel().getSelectedItem();
        if(s != null)
            submissionConsumer.deleteSubmission(s.getId(), credentials);
       refreshTable();
    }

    @FXML
    public void updateAssignComboBox(){
        this.assignmentComboBox.setItems(FXCollections.observableArrayList(assignmentConsumer.getAllAssignments(credentials)));
    }

    @FXML
    public void submitBtnClicked(){
        Assignment a = (Assignment) assignmentComboBox.getValue();
        if(a != null){
            int id = studentId;
            submissionConsumer.addSubmission(new SubmissionRequestModel(a.getId(), id, descriptionArea.getText()), credentials);
        }
    }

    private void refreshTable(){
        List<Submission> sList = submissionConsumer.getAllSubmissions(credentials).stream().filter(s -> s.getStudentUsername().equals(credentials.getUsername())).collect(Collectors.toList());
        submissionTable.setItems(FXCollections.observableArrayList(sList));
    }


    public void setCredentials(LoginModel credentials){
        this.credentials = credentials;
    }
}
