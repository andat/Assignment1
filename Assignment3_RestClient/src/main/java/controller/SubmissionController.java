package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.Submission;
import consumer.SubmissionConsumer;
import consumerContracts.ISubmissionConsumer;

public class SubmissionController {

    private ISubmissionConsumer submissionService;

    @FXML
    TableView submissionTable;

    public SubmissionController(){
        this.submissionService = new SubmissionConsumer();
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
        Submission s = (Submission) submissionTable.getSelectionModel().getSelectedItem();
        if(s != null)
            submissionService.deleteSubmission(s.getId());
        refreshTable();
    }

    @FXML
    public void addBtnClicked(){

    }

    private void refreshTable(){
        submissionTable.setItems(FXCollections.observableArrayList(submissionService.getAllSubmissions()));
    }
}
