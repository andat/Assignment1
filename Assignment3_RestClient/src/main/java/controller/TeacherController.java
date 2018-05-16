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

    private LoginModel credentials;

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AssignmentsTableView.fxml"));
            Parent root = loader.load();
            AssignmentsTableController controller = loader.getController();
            controller.setCredentials(credentials);

            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/StudentTableView.fxml"));
            Parent root1 = loader1.load();
            StudentTableController controller1 = loader1.getController();
            controller1.setCredentials(credentials);

            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/LabTableView.fxml"));
            Parent root2 = loader2.load();
            LabTableController controller2 = loader2.getController();
            controller2.setCredentials(credentials);

            FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/AttendanceTableView.fxml"));
            Parent root3 = loader3.load();
            AttendanceController controller3 = loader3.getController();
            controller3.setCredentials(credentials);

            FXMLLoader loader4 = new FXMLLoader(getClass().getResource("/SubmissionTableView.fxml"));
            Parent root4 = loader4.load();
            SubmissionController controller4 = loader4.getController();
            controller4.setCredentials(credentials);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
