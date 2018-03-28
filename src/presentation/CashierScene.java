package presentation;

import business.model.SeatModel;
import business.model.ShowModel;
import business.model.TicketModel;
import business.model.UserModel;
import business.service.*;
import javafx.beans.property.SimpleStringProperty;
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
    ISeatService seatService;
    private TableView table;
    private ChoiceBox<ShowModel> showList;
    private ChoiceBox<SeatModel> seatList;

    public CashierScene(Pane pane, UserModel loggedUser, Stage window, Scene prevScene) {
        super(pane, 1000, 600);
        this.loggedUser = loggedUser;
        this.showService = new ShowService();
        this.ticketService = new TicketService();
        this.seatService = new SeatService();
        this.table = new TableView();
        this.showList = new ChoiceBox(getShows());
        showList.getSelectionModel().selectFirst();
        this.seatList = new ChoiceBox(getSeats());
        seatList.getSelectionModel().selectFirst();

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
        labelBox.getChildren().addAll(selectLabel, showList, seatList);

        //options pane
        HBox optionsPane = new HBox(8);
        optionsPane.setPadding(new Insets(10));
        Button sellBtn = new Button("Sell ticket");
        sellBtn.setOnAction(e -> sellTicket(showList.getValue(), seatList.getValue()));

        Button allTicketsBtn = new Button("See sold tickets");
        allTicketsBtn.setOnAction(e -> createSoldTicketsTable(showList.getValue()));

        Button editBtn = new Button("Edit seat for reservation");
        editBtn.setOnAction(e -> editSeat(showList.getValue()));

        Button cancelBtn = new Button("Cancel reservation");
        cancelBtn.setOnAction(e -> cancelReservation(showList.getValue()));

        optionsPane.setAlignment(Pos.CENTER);
        optionsPane.getChildren().addAll(sellBtn, allTicketsBtn, editBtn, cancelBtn);

        centerPane.getChildren().addAll(labelBox, optionsPane);
        centerPane.getChildren().add(table);
        ((BorderPane) pane).setCenter(centerPane);
    }

    private ObservableList<ShowModel> getShows(){
        return FXCollections.observableArrayList(this.showService.findAll());
    }

    private ObservableList<SeatModel> getSeats(){
        return FXCollections.observableArrayList(this.seatService.findAll());
    }

    private void sellTicket(ShowModel show, SeatModel seat){
        try {
            this.showService.sellTicket(seat, show);
            //table.setItems(FXCollections.observableArrayList(this.showService.findSoldTickets(show)));
        } catch(Exception e){
           AlertBox.display("Could not sell ticket", e.getMessage());
        }
    }

    private void createSoldTicketsTable(ShowModel show){
        //System.out.println(show.getTitle());
        TableColumn<TicketModel, Integer> idColumn = new TableColumn<>("Ticket id");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<TicketModel, String> showColumn = new TableColumn<>("Show");
        showColumn.setMinWidth(50);
        showColumn.setCellValueFactory(cellData -> new SimpleStringProperty(show.getTitle()));

        TableColumn<TicketModel, String> seatColumn = new TableColumn<>("Seat id");
        seatColumn.setMinWidth(100);
        seatColumn.setCellValueFactory(cellData -> new SimpleStringProperty(seatService.findById(cellData.getValue().getSeatid()).toString()));
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

    public void cancelReservation(ShowModel show){
        ObservableList<TicketModel> selectedTickets = table.getSelectionModel().getSelectedItems();
        if(!selectedTickets.isEmpty()) {
            this.showService.cancelBooking(selectedTickets.get(0));
            table.setItems(FXCollections.observableArrayList(this.showService.findSoldTickets(show)));
        }
    }

    public void editSeat(ShowModel show){
        ObservableList<TicketModel> selectedTickets = table.getSelectionModel().getSelectedItems();
        if(!selectedTickets.isEmpty()){
            SeatModel seat = seatList.getValue();
            if(seat.getId() != selectedTickets.get(0).getSeatid()) {
                this.ticketService.changeSeat(selectedTickets.get(0), seat.getId());
                table.setItems(FXCollections.observableArrayList(this.showService.findSoldTickets(show)));
            }
        }
    }
}
