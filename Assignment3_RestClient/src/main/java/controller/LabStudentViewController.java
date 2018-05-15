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


import java.net.URL;
import java.util.ResourceBundle;

public class LabStudentViewController implements Initializable{

    private ILabConsumer labConsumer;

    @FXML
    TextField filterField;

    @FXML
    TableView<Laboratory> labTable;

    public LabStudentViewController(){
        this.labConsumer = new LabConsumer();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable();
    }

    @FXML
    public void viewBtnClicked(){
        refreshTable();
    }

    @FXML
    public void filterBtnClicked(){
        String keyword = filterField.getText();
        labTable.setItems(FXCollections.observableArrayList(labConsumer.getFilteredLaboratories(keyword)));
    }

    private void refreshTable(){
        this.labTable.setItems(FXCollections.observableArrayList(labConsumer.getAllLaboratories()));
    }
}
