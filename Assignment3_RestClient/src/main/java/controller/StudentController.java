package controller;

import consumerContracts.IStudentConsumer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import model.request.LoginModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable{

    @FXML
    Button logOutBtn;

    @FXML
    TabPane studentTabPane;

    private AssignmentStudentViewController assignmentStudentViewController;
    private LabStudentViewController labStudentViewController;
    private SubmissionsStudentViewController submissionsStudentViewController;

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

    @FXML
    public void passBtnClicked(){
        PasswordBox.display("Change password", "Enter new password:", credentials);
    }

    public void setCredentials(LoginModel credentials){
        this.credentials = credentials;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LabStudentView.fxml"));
            Parent root = loader.load();
            LabStudentViewController controller = loader.getController();
            controller.setCredentials(credentials);

            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/AssignmentStudentView.fxml"));
            Parent root1 = loader1.load();
            AssignmentStudentViewController controller1 = loader1.getController();
            controller1.setCredentials(credentials);

            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/SubmissionsStudentView.fxml"));
            Parent root2 = loader2.load();
            SubmissionsStudentViewController controller2 = loader2.getController();
            controller2.setCredentials(credentials);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
