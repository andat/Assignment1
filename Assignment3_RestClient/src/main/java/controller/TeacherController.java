package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class TeacherController {

    private Parent studentsView;

    @FXML
    Button logOutBtn;

    @FXML
    Tab studentsTab;

    @FXML
    Tab labsTab;

    @FXML
    Tab attendanceTab;

    @FXML
    Tab assignmentsTab;

    @FXML
    Tab submissionsTab;

    @FXML
    TabPane teacherTabPane;

    @FXML
    public void logOutBtnClicked(){

    }

    public void studentsTabSelected(){
    }

    public void assignmentsTabSelected(){

    }

    public void labsTabSelected() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LabTableView.fxml"));
        loader.load();
        LabTableController ctrl = loader.getController();
        ctrl.refreshTable();
    }

    public void attendanceTabSelected(){

    }

    public void submissionTabSelected(){

    }
}
