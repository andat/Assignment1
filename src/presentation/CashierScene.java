package presentation;

import business.model.ShowModel;
import business.model.TicketModel;
import business.model.UserModel;
import business.service.IShowService;
import business.service.ITicketService;
import business.service.ShowService;
import business.service.TicketService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CashierScene extends Scene {
    private UserModel loggedUser;
    private IShowService showService;
    private ITicketService ticketService;
    private TableView table;
    private ChoiceBox<ShowModel> showList;

    public CashierScene(Pane pane, UserModel loggedUser, Stage window, Scene prevScene) {
        super(pane, 1000, 600);
        this.loggedUser = loggedUser;
        this.showService = new ShowService();
        this.ticketService = new TicketService();
        this.table = new TableView();
        this.showList = new ChoiceBox(getShows());

        //set up scene
        //top
        HBox topPane = new HBox(10);
        Label loggedLabel = new Label(" You are logged in as " + loggedUser.getUsername() + ".");
        Button logOutBtn = new Button("Log out");
        logOutBtn.setOnAction(e -> window.setScene(prevScene));
        topPane.setAlignment(Pos.CENTER_RIGHT);
        topPane.getChildren().addAll(loggedLabel, logOutBtn);
        topPane.setPrefHeight(30);
        ((BorderPane) pane).setTop(topPane);

        //center
        VBox centerPane = new VBox(20);
        //select show
        Label selectLabel = new Label(" Select show:");
        selectLabel.setFont(new Font(13));


        HBox labelBox = new HBox(20);
        labelBox.setAlignment(Pos.CENTER_LEFT);
        labelBox.getChildren().addAll(selectLabel, showList);

        //options pane
        HBox optionsPane = new HBox(8);
        optionsPane.setPadding(new Insets(10));
        Button sellBtn = new Button("Sell ticket");

        Button allTicketsBtn = new Button("See sold tickets");
        allTicketsBtn.setOnAction(e -> createSoldTicketsTable(showList.getValue()));

        Button editBtn = new Button("Edit seat for reservation");

        Button cancelBtn = new Button("Cancel reservation");

        optionsPane.setAlignment(Pos.CENTER);
        optionsPane.getChildren().addAll(sellBtn, allTicketsBtn, editBtn, cancelBtn);

        centerPane.getChildren().addAll(labelBox, optionsPane);
        centerPane.getChildren().add(table);
        ((BorderPane) pane).setCenter(centerPane);
    }

    private ObservableList<ShowModel> getShows(){
        return FXCollections.observableArrayList(this.showService.findAll());
    }

    private void createSoldTicketsTable(ShowModel show){
        //System.out.println(show.getTitle());
        TableColumn<TicketModel, Integer> idColumn = new TableColumn<>("Ticket id");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<ShowModel, String> showColumn = new TableColumn<>("Show");
        showColumn.setMinWidth(50);
        showColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<TicketModel, String> seatColumn = new TableColumn<>("Seat");
        seatColumn.setMinWidth(100);
        seatColumn.setCellValueFactory(new PropertyValueFactory<>("seat_id"));
//        seatColumn.setCellFactory(TextFieldTableCell.<UserModel>forTableColumn());
//        seatColumn.setOnEditCommit(
//                (TableColumn.CellEditEvent<UserModel, String> t) -> {
//                    ((UserModel) t.getTableView().getItems().get(
//                            t.getTablePosition().getRow())
//                    ).setPassword(t.getNewValue());
//                });

        TableColumn<TicketModel, Boolean> bookedColumn = new TableColumn<>("Booked");
        bookedColumn.setMinWidth(100);
        bookedColumn.setCellValueFactory(new PropertyValueFactory<>("booked"));

        table.setItems(FXCollections.observableArrayList(this.showService.findSoldTickets(show)));
        table.getColumns().setAll(idColumn, showColumn, seatColumn, bookedColumn);
    }
}
