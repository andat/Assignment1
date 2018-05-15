package controller;

import consumer.AssignmentConsumer;
import consumer.SubmissionConsumer;
import consumerContracts.IAssignmentConsumer;
import consumerContracts.ISubmissionConsumer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import model.Assignment;
import model.Submission;
import model.request.SubmissionRequestModel;

import java.net.URL;
import java.util.ResourceBundle;

public class SubmissionsStudentViewController implements Initializable{
    private ISubmissionConsumer submissionConsumer;
    private IAssignmentConsumer assignmentConsumer;

    @FXML
    TableView<Submission> submissionTable;

    @FXML
    ComboBox assignmentComboBox;

    @FXML
    TextArea descriptionArea;

    public SubmissionsStudentViewController(){
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
    public void updateAssignComboBox(){
        this.assignmentComboBox.setItems(FXCollections.observableArrayList(assignmentConsumer.getAllAssignments()));
    }

    @FXML
    public void submitBtnClicked(){
        Assignment a = (Assignment) assignmentComboBox.getValue();
        if(a != null){
            //TODO get student id from login info
            int id = 1;
            submissionConsumer.addSubmission(new SubmissionRequestModel(a.getId(), id, descriptionArea.getText()));
        }
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
