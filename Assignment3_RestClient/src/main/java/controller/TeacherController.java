package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import model.request.LoginModel;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherController implements Initializable{

    @FXML
    Button logOutBtn;

    @FXML
    TabPane teacherTabPane;

    private LoginModel credentials;

    public TeacherController(LoginModel credentials) {
        this.credentials = credentials;
    }

    @FXML
    public void logOutBtnClicked(){
        Stage stage1 = (Stage) logOutBtn.getScene().getWindow();
        stage1.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("SD Lab Application");
            stage.setScene(new Scene(root, 380, 280));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setCredentials(LoginModel credentials){
        this.credentials = credentials;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/StudentTableView.fxml"));
            loader1.setController(new StudentTableController(credentials));
            Parent root1 = loader1.load();
            teacherTabPane.getTabs().get(0).setContent(root1);

            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/LabTableView.fxml"));
            loader2.setController(new LabTableController(credentials));
            Parent root2 = loader2.load();
            teacherTabPane.getTabs().get(1).setContent(root2);


            FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/AttendanceTableView.fxml"));
            loader3.setController(new AttendanceController(credentials));
            Parent root3 = loader3.load();
            teacherTabPane.getTabs().get(2).setContent(root3);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AssignmentsTableView.fxml"));
            loader.setController(new AssignmentsTableController(credentials));
            Parent root = loader.load();
            teacherTabPane.getTabs().get(3).setContent(root);

            FXMLLoader loader4 = new FXMLLoader(getClass().getResource("/SubmissionTableView.fxml"));
            loader4.setController(new SubmissionController(credentials));
            Parent root4 = loader4.load();
            teacherTabPane.getTabs().get(4).setContent(root4);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
