package controller;

import consumer.LabConsumer;
import consumerContracts.ILabConsumer;
import consumerContracts.IStudentConsumer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Laboratory;
import model.request.LoginModel;


import java.net.URL;
import java.util.ResourceBundle;

public class LabStudentViewController implements Initializable{

    private ILabConsumer labConsumer;
    private LoginModel credentials;

    @FXML
    TextField filterField;

    @FXML
    TableView<Laboratory> labTable;

    public LabStudentViewController(LoginModel credentials) {
        this.labConsumer = new LabConsumer();
        this.credentials = credentials;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(credentials != null)
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

    private void refreshTable(){
        System.out.println(credentials);
        this.labTable.setItems(FXCollections.observableArrayList(labConsumer.getAllLaboratories(credentials)));
    }

    public void setCredentials(LoginModel credentials){
        this.credentials = credentials;
    }
}
