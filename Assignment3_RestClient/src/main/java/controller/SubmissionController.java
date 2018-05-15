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

import java.net.URL;
import java.util.ResourceBundle;

public class SubmissionController implements Initializable{

    private ISubmissionConsumer submissionConsumer;
    private IAssignmentConsumer assignmentConsumer;

    @FXML
    TableView<Submission> submissionTable;

    @FXML
    TextField gradeField;

    @FXML
    ComboBox assignmentComboBox;

    public SubmissionController(){
        this.submissionConsumer = new SubmissionConsumer();
        this.assignmentConsumer = new AssignmentConsumer();
    }

    @FXML
    public void viewBtnClicked(){
        refreshTable();
    }

    @FXML
    public void deleteBtnClicked(){
        Submission s = (Submission) submissionTable.getSelectionModel().getSelectedItem();
        if(s != null)
            submissionConsumer.deleteSubmission(s.getId());
        refreshTable();
    }


    @FXML
    public void gradeBtnClicked(){
        Submission s  = submissionTable.getSelectionModel().getSelectedItem();
        int id = -1;
        if(s != null)
            id = s.getId();
        int grade = 0;
        try {
            grade = Integer.parseInt(gradeField.getText());
        } catch(NumberFormatException e){
            System.out.println("Please input a number between 1 and 10.");
        }
        if(grade >= 1 && grade <= 10 && id != -1){
            submissionConsumer.gradeSubmission(id, grade);
        }
        refreshTable();
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
            this.submissionTable.setItems(FXCollections.observableArrayList(submissionConsumer.getSubmissionByAssignmentId(id)));
        }
    }

    @FXML
    public void updateAssignComboBox(){
        this.assignmentComboBox.setItems(FXCollections.observableArrayList(assignmentConsumer.getAllAssignments()));
    }

    private void refreshTable(){
        submissionTable.setItems(FXCollections.observableArrayList(submissionConsumer.getAllSubmissions()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.assignmentComboBox.setItems(FXCollections.observableArrayList(assignmentConsumer.getAllAssignments()));
        refreshTable();
    }
}
