package controller;

import consumerContracts.IStudentConsumer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    public StudentController(LoginModel credentials){
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
            loader.setController(new LabStudentViewController(credentials));
            Parent root = loader.load();
            studentTabPane.getTabs().get(0).setContent(root);


            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/AssignmentStudentView.fxml"));
            loader1.setController(new AssignmentStudentViewController(credentials));
            Parent root1 = loader1.load();
            studentTabPane.getTabs().get(1).setContent(root1);


            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/SubmissionsStudentView.fxml"));
            loader2.setController(new SubmissionsStudentViewController(credentials));
            Parent root2 = loader2.load();
            studentTabPane.getTabs().get(2).setContent(root2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
