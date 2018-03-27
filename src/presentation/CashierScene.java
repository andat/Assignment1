package presentation;

import business.model.UserModel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class CashierScene extends Scene {
    private UserModel loggedUser;

    public CashierScene(UserModel loggedUser) {
        super(new BorderPane(), 400, 500);
        this.loggedUser = loggedUser;
    }
}
