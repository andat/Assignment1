package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SDLabClientApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException{
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/StudentView.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginView.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("SD Laboratory Application");
        primaryStage.setScene(new Scene(root, 380, 280));
        primaryStage.show();
    }
}
