package controller;

import client.HttpClient;
import consumer.LoginConsumer;
import consumer.StudentConsumer;
import consumerContracts.ILoginConsumer;
import consumerContracts.IStudentConsumer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.request.LoginModel;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    private ILoginConsumer loginConsumer;
    private IStudentConsumer studentConsumer;

    @FXML
    Button loginBtn;

    @FXML
    TextField usernameField;

    @FXML
    PasswordField passField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.loginConsumer = new LoginConsumer();
        this.studentConsumer = new StudentConsumer();
    }

    @FXML
    public void loginBtnClicked(){
        String username = usernameField.getText();
        String password = passField.getText();

        String role = this.loginConsumer.login(username, password);

        LoginModel credentials = new LoginModel(username, password);
        if(role == null){
            AlertBox.display("InvalidCredentials", "Wrong username or password!");
        } else if(role.equals("\"STUDENT\"")){
            Stage stage1 = (Stage) loginBtn.getScene().getWindow();
            stage1.close();
            String newPass = password;

            if(!studentConsumer.findByUsername(username, credentials).isPasswordSet()){
                newPass = PasswordBox.display("First time login", "Please set a password", credentials);
            };

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/StudentView.fxml"));
                credentials.setPassword(newPass);
                loader.setController(new StudentController(credentials));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("SD Lab Application - student");
                stage.setScene(new Scene(root, 900, 600));
                stage.show();
            } catch (IOException e){
                e.printStackTrace();
            }
        } else if(role.equals("\"TEACHER\"")) {
            Stage stage1 = (Stage) loginBtn.getScene().getWindow();
            stage1.close();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/TeacherView.fxml"));
                loader.setController(new TeacherController(credentials));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("SD Lab Application - teacher");
                stage.setScene(new Scene(root, 900, 600));
                stage.show();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
