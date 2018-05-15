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
import model.Laboratory;

import java.net.URL;
import java.util.ResourceBundle;

public class AssignmentStudentViewController implements Initializable{
    private IAssignmentConsumer assignmentConsumer;

    private ILabConsumer labConsumer;

    @FXML
    TableView assignmentTable;

    @FXML
    ComboBox<Laboratory> labComboBox;

    public AssignmentStudentViewController(){
        this.assignmentConsumer = new AssignmentConsumer();
        this.labConsumer = new LabConsumer();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.labComboBox.setItems(FXCollections.observableArrayList(labConsumer.getAllLaboratories()));
        refreshTable();
    }

    @FXML
    public void viewBtnClicked(){
        refreshTable();
    }

    private void refreshTable(){
        assignmentTable.setItems(FXCollections.observableArrayList(assignmentConsumer.getAllAssignments()));
    }

    @FXML
    public void updateLabComboBox(){
        this.labComboBox.setItems(FXCollections.observableArrayList(labConsumer.getAllLaboratories()));
    }

    @FXML
    public void labBtnClicked(){
        Laboratory selLab = labComboBox.getValue();
        if(selLab != null){
            int labId = selLab.getId();
            this.assignmentTable.setItems(FXCollections.observableArrayList(assignmentConsumer.getAssignmentsByLabId(labId)));
        }
    }
}
