package controller;

import consumer.StudentConsumer;
import consumerContracts.IStudentConsumer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Student;
import model.request.LoginModel;

import java.util.Collection;

public class PasswordBox {

    public static void display(String title, String message, LoginModel credentials){
        Stage window = new Stage();
        StudentConsumer studentConsumer = new StudentConsumer();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(500);
        window.setMinHeight(300);

        Label label = new Label(message);
        PasswordField newPassField = new PasswordField();
        newPassField.setPromptText("new password");
        newPassField.setMaxWidth(200);

        Button changeBtn = new Button("Change password");
        changeBtn.setOnAction(e -> studentConsumer.changePassword(newPassField.getText(), credentials));

        VBox layout = new VBox(10);
        layout.getChildren().add(label);
        layout.getChildren().add(newPassField);
        layout.getChildren().add(changeBtn);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
