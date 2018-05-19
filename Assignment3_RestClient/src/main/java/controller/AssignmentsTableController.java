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
import model.request.LoginModel;
import util.DateUtil;
import consumerContracts.IAssignmentConsumer;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class AssignmentsTableController implements Initializable{

    private IAssignmentConsumer assignmentConsumer;

    private ILabConsumer labConsumer;

    private LoginModel credentials;

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


    public AssignmentsTableController(LoginModel credentials){
        this.assignmentConsumer = new AssignmentConsumer();
        this.labConsumer = new LabConsumer();
        this.credentials = credentials;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateUtil.setupDatePicker(deadlinePicker);
        if(credentials != null){
            this.labComboBox.setItems(FXCollections.observableArrayList(labConsumer.getAllLaboratories(credentials)));
            refreshTable();
        }
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
            assignmentConsumer.editAssignment(a, selAssignment.getId(), credentials);
        }
        refreshTable();
        clearFields();
    }

    @FXML
    public void deleteBtnClicked(){
        Assignment selectedAssignment = (Assignment) assignmentTable.getSelectionModel().getSelectedItem();
        if(selectedAssignment != null){
            assignmentConsumer.deleteAssignment(selectedAssignment.getId(), credentials);
            //System.out.println(selectedAssignment.getId());
        }
        refreshTable();
        clearFields();
    }

    @FXML
    public void addBtnClicked(){
        AssignmentRequestModel a = getAssignmentFromFields();
        if(a != null) {
            this.assignmentConsumer.addAssignment(a, credentials);
            refreshTable();
            clearFields();
        } else{
            AlertBox.display("Empty fields", "Some fields that are required are empty.");
        }


    }

    private void refreshTable(){
        assignmentTable.setItems(FXCollections.observableArrayList(assignmentConsumer.getAllAssignments(credentials)));
    }

    private AssignmentRequestModel getAssignmentFromFields(){
        String name = this.nameField.getText();
        String description = this.descriptionField.getText();
        Date deadline = null;
        if(this.deadlinePicker.getValue() != null)
            java.sql.Date.valueOf(this.deadlinePicker.getValue());
        Laboratory lab = null;
        if(this.labComboBox.getValue() != null)
            lab = this.labComboBox.getValue();

        if(lab != null && deadline != null && !name.isEmpty())
            return new AssignmentRequestModel(name, deadline, description, lab.getId());

        return null;
    }

    private void clearFields(){
        this.nameField.clear();
        this.descriptionField.clear();
    }

    @FXML
    public void updateLabComboBox(){
        this.labComboBox.setItems(FXCollections.observableArrayList(labConsumer.getAllLaboratories(credentials)));
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
            this.assignmentTable.setItems(FXCollections.observableArrayList(assignmentConsumer.getAssignmentsByLabId(labId, credentials)));
        } else {
            AlertBox.display("No laboratory selected", "Please select a laboratory.");
        }
    }

    public void setCredentials(LoginModel credentials){
        this.credentials = credentials;
    }
}
