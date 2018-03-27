package presentation;

import business.model.UserModel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class AdminScene  extends Scene {
    private UserModel loggedUser;

    public AdminScene(UserModel loggedUser) {
        super(new BorderPane(), 600, 800);
        this.loggedUser = loggedUser;
    }


}
