package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Laboratory;
import model.request.LabRequestModel;
import model.request.LoginModel;
import util.DateUtil;
import consumer.LabConsumer;
import consumerContracts.ILabConsumer;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class LabTableController implements Initializable{

    private ILabConsumer labConsumer;
    private ObservableList<Integer> numbers = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
    private LoginModel credentials;

    @FXML
    TableView<Laboratory> labTable;

    @FXML
    TextField filterField;

    @FXML
    DatePicker datePicker;

    @FXML
    TextField titleField;

    @FXML
    TextField curriculaField;

    @FXML
    TextField descriptionField;

    @FXML
    ComboBox numberComboBox;

    public LabTableController(LoginModel credentials){
        this.labConsumer = new LabConsumer();
        this.credentials = credentials;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.numberComboBox.setItems(numbers);
        DateUtil.setupDatePicker(datePicker);
        refreshTable();
    }

    @FXML
    public void viewBtnClicked(){
        refreshTable();
    }

    @FXML
    public void filterBtnClicked(){
        String keyword = filterField.getText();
        labTable.setItems(FXCollections.observableArrayList(labConsumer.getFilteredLaboratories(keyword, credentials)));
    }

    @FXML
    public void editBtnClicked(){
        LabRequestModel lab = getLabFromFields();
        int id = labTable.getSelectionModel().getSelectedItem().getId();
        if(lab != null){
            labConsumer.editLab(lab, id, credentials);
            refreshTable();
            clearFields();
            //System.out.println(id);
        }
    }

    @FXML
    public void rowSelected(){
        Laboratory selectedLab = labTable.getSelectionModel().getSelectedItem();

        if(selectedLab != null){
            String title = selectedLab.getTitle();
            String curricula = selectedLab.getCurricula();
            String description = selectedLab.getDescription();
            int number = selectedLab.getNumber();
            Date date = selectedLab.getDate();

            this.titleField.setText(title);
            this.curriculaField.setText(curricula);
            this.descriptionField.setText(description);
            this.numberComboBox.setValue(number);
            this.datePicker.setValue(date.toLocalDate());
        }
    }

    //TODO warn user he is about to delete all assignments related to lab also + submissions?
    @FXML
    public void deleteBtnClicked(){
        Laboratory selectedLab = labTable.getSelectionModel().getSelectedItem();
        if(selectedLab != null){
            labConsumer.deleteLab(selectedLab.getId(), credentials);
        }
        refreshTable();
    }

    @FXML
    public void addBtnClicked(){
        LabRequestModel lab = getLabFromFields();
        if(lab != null){
            if(labConsumer.addLaboratory(lab, credentials) == 500){
                AlertBox.display("An error occurred", "A laboratory with that number already exists.");
            }
            refreshTable();
            clearFields();
        } else{
            AlertBox.display("Empty fields", "Some fields that are required are empty.");
        }
    }

    private LabRequestModel getLabFromFields(){
        String title = this.titleField.getText();
        String curricula = this.curriculaField.getText();
        String description = this.descriptionField.getText();
        int number = 0;
        if(numberComboBox.getValue() != null)
            number = (int) numberComboBox.getValue();
        Date date = null;
        if(datePicker.getValue() != null)
            date = java.sql.Date.valueOf(datePicker.getValue());

        if(number != 0 || date != null) {
            LabRequestModel lab = new LabRequestModel(number, date, title, curricula, description);
            return lab;
        } else
            return null;
    }

    public void refreshTable(){
        labTable.setItems(FXCollections.observableArrayList(labConsumer.getAllLaboratories(credentials)));
    }

    private void clearFields(){
        this.titleField.clear();
        this.curriculaField.clear();
        this.descriptionField.clear();
    }

    public void setCredentials(LoginModel credentials){
        this.credentials = credentials;
    }
}
