package controller;

import consumer.LabConsumer;
import consumerContracts.ILabConsumer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Assignment;
import consumer.AssignmentConsumer;
import javafx.scene.control.TableView;
import model.Laboratory;
import model.request.AssignmentRequestModel;
import util.DateUtil;
import consumerContracts.IAssignmentConsumer;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class AssignmentsTableController implements Initializable{

    private IAssignmentConsumer assignmentConsumer;

    private ILabConsumer labConsumer;

    @FXML
    TableView assignmentTable;

    @FXML
    DatePicker deadlinePicker;

    @FXML
    TextField nameField;

    @FXML
    TextField descriptionField;

    @FXML
    ComboBox<Laboratory> labComboBox;


    public AssignmentsTableController(){
        this.assignmentConsumer = new AssignmentConsumer();
        this.labConsumer = new LabConsumer();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateUtil.setupDatePicker(deadlinePicker);
        this.labComboBox.setItems(FXCollections.observableArrayList(labConsumer.getAllLaboratories()));
        refreshTable();
    }

    @FXML
    public void viewBtnClicked(){
        refreshTable();
    }

    @FXML
    public void editBtnClicked(){
        AssignmentRequestModel a = getAssignmentFromFields();
        Assignment selAssignment = (Assignment) assignmentTable.getSelectionModel().getSelectedItem();
        if(selAssignment != null){
            assignmentConsumer.editAssignment(a, selAssignment.getId());
        }
        refreshTable();
    }

    @FXML
    public void deleteBtnClicked(){
        Assignment selectedAssignment = (Assignment) assignmentTable.getSelectionModel().getSelectedItem();
        if(selectedAssignment != null){
            assignmentConsumer.deleteAssignment(selectedAssignment.getId());
            //System.out.println(selectedAssignment.getId());
        }
        refreshTable();
    }

    @FXML
    public void addBtnClicked(){
        AssignmentRequestModel a = getAssignmentFromFields();
        this.assignmentConsumer.addAssignment(a);
        refreshTable();
        clearFields();
    }

    private void refreshTable(){
        assignmentTable.setItems(FXCollections.observableArrayList(assignmentConsumer.getAllAssignments()));
    }

    private AssignmentRequestModel getAssignmentFromFields(){
        //TODO validate
        String name = this.nameField.getText();
        String description = this.descriptionField.getText();
        Date deadline = java.sql.Date.valueOf(this.deadlinePicker.getValue());
        Laboratory lab = this.labComboBox.getValue();

        return new AssignmentRequestModel(name, deadline, description, lab.getId());
    }

    private void clearFields(){
        this.nameField.clear();
        this.descriptionField.clear();
    }

    @FXML
    public void updateLabComboBox(){
        this.labComboBox.setItems(FXCollections.observableArrayList(labConsumer.getAllLaboratories()));
    }

    @FXML
    public void rowSelected(){
        Assignment a = (Assignment) assignmentTable.getSelectionModel().getSelectedItem();
        if(a != null){
            this.descriptionField.setText(a.getDescription());
            this.nameField.setText(a.getName());
            this.deadlinePicker.setValue(a.getDeadline().toLocalDate());
            Laboratory l = labComboBox.getItems().filtered(lab -> lab.getNumber() == a.getLaboratoryNumber()).get(0);
            this.labComboBox.setValue(l);
        }
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
