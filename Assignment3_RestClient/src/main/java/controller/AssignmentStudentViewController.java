package controller;

import consumer.AssignmentConsumer;
import consumer.LabConsumer;
import consumerContracts.IAssignmentConsumer;
import consumerContracts.ILabConsumer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import model.Assignment;
import model.Laboratory;
import model.request.LoginModel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AssignmentStudentViewController implements Initializable{
    private IAssignmentConsumer assignmentConsumer;

    private ILabConsumer labConsumer;

    private LoginModel credentials;

    @FXML
    TableView assignmentTable;

    @FXML
    ComboBox<Laboratory> labComboBox;

    public AssignmentStudentViewController(LoginModel credentials){
        this.assignmentConsumer = new AssignmentConsumer();
        this.labConsumer = new LabConsumer();
        this.credentials = credentials;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(credentials != null) {
            this.labComboBox.setItems(FXCollections.observableArrayList(labConsumer.getAllLaboratories(credentials)));
            refreshTable();
        }
    }

    @FXML
    public void viewBtnClicked(){
        refreshTable();
    }

    private void refreshTable(){
        if(credentials != null) {
            List<Assignment> list = assignmentConsumer.getAllAssignments(credentials);
            assignmentTable.setItems(FXCollections.observableArrayList(list));
        }
    }

    @FXML
    public void updateLabComboBox(){
        this.labComboBox.setItems(FXCollections.observableArrayList(labConsumer.getAllLaboratories(credentials)));
    }

    @FXML
    public void labBtnClicked(){
        Laboratory selLab = labComboBox.getValue();
        if(selLab != null){
            int labId = selLab.getId();
            this.assignmentTable.setItems(FXCollections.observableArrayList(assignmentConsumer.getAssignmentsByLabId(labId, credentials)));
        }
    }

    public void setCredentials(LoginModel credentials){
        this.credentials = credentials;
    }
}
