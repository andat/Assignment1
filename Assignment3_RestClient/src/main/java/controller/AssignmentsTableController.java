package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import model.Assignment;
import consumer.AssignmentConsumer;
import javafx.scene.control.TableView;
import util.DateUtil;
import consumerContracts.IAssignmentConsumer;

public class AssignmentsTableController {

    private IAssignmentConsumer assignmentService;

    @FXML
    TableView assignmentTable;

    @FXML
    DatePicker deadlinePicker;

    public AssignmentsTableController(){
        this.assignmentService = new AssignmentConsumer();
        deadlinePicker = new DatePicker();
        DateUtil.setupDatePicker(deadlinePicker);
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
        Assignment selectedAssignment = (Assignment) assignmentTable.getSelectionModel().getSelectedItem();
        if(selectedAssignment != null){
            assignmentService.deleteAssignment(selectedAssignment.getId());
        }
        refreshTable();
    }

    @FXML
    public void addBtnClicked(){

    }

    private void refreshTable(){
        assignmentTable.setItems(FXCollections.observableArrayList(assignmentService.getAllAssignments()));
    }
}
