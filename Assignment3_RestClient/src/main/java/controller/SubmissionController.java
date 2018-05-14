package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Submission;
import consumer.SubmissionConsumer;
import consumerContracts.ISubmissionConsumer;

public class SubmissionController {

    private ISubmissionConsumer submissionConsumer;

    @FXML
    TableView<Submission> submissionTable;

    @FXML
    TextField gradeField;

    public SubmissionController(){
        this.submissionConsumer = new SubmissionConsumer();
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
        int id  = submissionTable.getSelectionModel().getSelectedItem().getId();
        int grade = Integer.parseInt(gradeField.getText());
        if(grade >= 1 && grade <= 10){
            submissionConsumer.gradeSubmission(id, grade);
        }
        refreshTable();
    }

    @FXML
    public void rowSelected(){
        int grade = submissionTable.getSelectionModel().getSelectedItem().getGrade();
        gradeField.setText(Integer.toString(grade));
    }

    private void refreshTable(){
        submissionTable.setItems(FXCollections.observableArrayList(submissionConsumer.getAllSubmissions()));
    }
}
