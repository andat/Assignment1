package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class TeacherController {

    private Parent studentsView;

    @FXML
    Button studentsBtn;

    @FXML
    Button laboratoriesBtn;

    @FXML
    Button attendanceBtn;

    @FXML
    Button assignmentsBtn;

    @FXML
    Button submissionsBtn;

    @FXML
    SubScene centerScene;

    @FXML
    public void studentsBtnClicked() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TeacherView.fxml"));
        this.studentsView = loader.load();
    }

    @FXML
    public void laboratoriesBtnClicked(){

    }

    @FXML
    public void attendanceBtnClicked(){

    }

    @FXML
    public void assignmentsBtnClicked(){

    }

    @FXML
    public void submissionsBtnClicked(){

    }
}
