package controller;

import consumer.AssignmentConsumer;
import consumerContracts.IAssignmentConsumer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Assignment;
import model.Submission;
import consumer.SubmissionConsumer;
import consumerContracts.ISubmissionConsumer;
import model.request.LoginModel;

import java.net.URL;
import java.util.ResourceBundle;

public class SubmissionController implements Initializable{

    private ISubmissionConsumer submissionConsumer;
    private IAssignmentConsumer assignmentConsumer;
    private LoginModel credentials;

    @FXML
    TableView<Submission> submissionTable;

    @FXML
    TextField gradeField;

    @FXML
    ComboBox assignmentComboBox;

    public SubmissionController(LoginModel credentials){
        this.submissionConsumer = new SubmissionConsumer();
        this.assignmentConsumer = new AssignmentConsumer();
        this.credentials = credentials;
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
    public void gradeBtnClicked(){
        Submission s  = submissionTable.getSelectionModel().getSelectedItem();
        if(s != null) {
            int id = s.getId();
            String gradeString = gradeField.getText();
            int grade = 0;
            if (gradeString.matches("[1-9]{1,2}")) {
                grade = Integer.parseInt(gradeString);
                if (grade >= 1 && grade <= 10)
                    submissionConsumer.gradeSubmission(id, grade, credentials);
            } else {
                AlertBox.display("Wrong grade format", "Please input the grade as a number between 1 and 10.");
            }
            refreshTable();
        } else {
            AlertBox.display("No submission selected", "Please select a submission to grade.");
        }
    }

    @FXML
    public void rowSelected(){
        int grade = submissionTable.getSelectionModel().getSelectedItem().getGrade();
        gradeField.setText(Integer.toString(grade));
    }

    @FXML
    public void viewGradesForAssignment(){
        Assignment a = (Assignment) assignmentComboBox.getValue();
        if(a != null){
            int id = a.getId();
            this.submissionTable.setItems(FXCollections.observableArrayList(submissionConsumer.getSubmissionByAssignmentId(id, credentials)));
        } else
            AlertBox.display("No assignment selected", "Please select an assignment.");
    }

    @FXML
    public void updateAssignComboBox(){
        this.assignmentComboBox.setItems(FXCollections.observableArrayList(assignmentConsumer.getAllAssignments(credentials)));
    }

    private void refreshTable(){
        submissionTable.setItems(FXCollections.observableArrayList(submissionConsumer.getAllSubmissions(credentials)));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.assignmentComboBox.setItems(FXCollections.observableArrayList(assignmentConsumer.getAllAssignments(credentials)));
        refreshTable();
    }

    public void setCredentials(LoginModel credentials){
        this.credentials = credentials;
    }
}
