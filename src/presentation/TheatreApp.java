package presentation;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TheatreApp extends Application {
    Stage window;
    Scene loginScene, cashierView, adminView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Login");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(10);
        grid.setHgap(8);

        Label label = new Label("Input your username and password to continue");
        GridPane.setConstraints(label, 0, 1, 2, 1);

        //username label
        Label usernameLabel = new Label("Username:");
        GridPane.setConstraints(usernameLabel, 0, 3);
        //username input
        TextField usernameInput = new TextField();
        usernameInput.setPromptText("username");
        GridPane.setConstraints(usernameInput, 1, 3);

        //password label
        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 0, 4);
        //password input
        TextField passwordInput = new TextField();
        passwordInput.setPromptText("password");
        GridPane.setConstraints(passwordInput, 1, 4);

        //Login button
        Button loginButton = new Button("Log in");
        GridPane.setConstraints(loginButton, 1, 5);

        grid.getChildren().addAll(label, usernameLabel, usernameInput, passLabel, passwordInput, loginButton);

        loginScene = new Scene(grid, 300, 250);
        window.setScene(loginScene);
        window.show();
    }
}
