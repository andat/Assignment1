package presentation;

import business.model.ShowModel;
import business.model.UserModel;
import business.service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import util.FormatType;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AdminScene  extends Scene {
    private UserModel loggedUser;
    private Scene prevScene;
    private TableView table;
    private Button editBtn;
    private Button addBtn;
    private Button deleteBtn;
    private IUserService userService;
    private IShowService showService;
    private HBox fieldBox;

    public AdminScene(Pane pane, UserModel loggedUser, Stage window, Scene prevScene) {
        super(pane, 1000, 600);
        this.loggedUser = loggedUser;
        this.table = new TableView();
        this.editBtn = new Button("Edit");
        this.addBtn = new Button("Add");
        this.deleteBtn = new Button("Delete");
        this.fieldBox = new HBox(10);

        this.userService = new UserService();
        this.showService = new ShowService();


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


        //left
        Pane optionsPane = new VBox(8);
        optionsPane.setPadding(new Insets(10));
        Button cashierBtn = new Button("Manage cashiers");
        cashierBtn.setOnAction(e -> {
            createCashierTable(table);
            setUserFieldsButtons();
        });

        Button showBtn = new Button("Manage shows");
        showBtn.setOnAction(e -> {
            createShowTable(table);
            setShowFieldsButtons();
        });

        optionsPane.getChildren().addAll(cashierBtn, showBtn);
        ((BorderPane) pane).setLeft(optionsPane);


        ((BorderPane) pane).setCenter(table);

        //bottom
        VBox bottom = new VBox(10);
        //buttons
        HBox bottomBox = new HBox();
        bottomBox.setSpacing(10);
        bottomBox.setPadding(new Insets(10));
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.getChildren().addAll(editBtn, deleteBtn);
        //add fields and buttons
        fieldBox.setPadding(new Insets(10));
        fieldBox.setAlignment(Pos.CENTER);
        bottom.getChildren().addAll(fieldBox, bottomBox);
        ((BorderPane) pane).setBottom(bottom);
    }


    private void createCashierTable(TableView table){
        TableColumn<UserModel, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<UserModel, String> userColumn = new TableColumn<>("Username");
        userColumn.setMinWidth(100);
        userColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        userColumn.setCellFactory(TextFieldTableCell.<UserModel>forTableColumn());
        userColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<UserModel, String> t) -> {
                    ((UserModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setUsername(t.getNewValue());
                });

        TableColumn<UserModel, String> passColumn = new TableColumn<>("Password");
        passColumn.setMinWidth(100);
        passColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        passColumn.setCellFactory(TextFieldTableCell.<UserModel>forTableColumn());
        passColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<UserModel, String> t) -> {
                    ((UserModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setPassword(t.getNewValue());
                });

        TableColumn<UserModel, Boolean> adminColumn = new TableColumn<>("Admin rights");
        adminColumn.setMinWidth(100);
        adminColumn.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));

        table.setItems(FXCollections.observableArrayList(this.userService.findAllCashiers()));
        table.getColumns().setAll(idColumn, userColumn, passColumn, adminColumn);
    }

    private void createShowTable(TableView table){
        table.setEditable(true);
        TableColumn<UserModel, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<ShowModel, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(150);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setCellFactory(TextFieldTableCell.<ShowModel>forTableColumn());
        titleColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<ShowModel, String> t) -> {
                    ((ShowModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setTitle(t.getNewValue());
                });

        TableColumn<ShowModel, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setMinWidth(100);
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        genreColumn.setCellFactory(TextFieldTableCell.<ShowModel>forTableColumn());
        genreColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<ShowModel, String> t) -> {
                    ((ShowModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setGenre(t.getNewValue());
                });

        TableColumn<ShowModel, String> distribColumn = new TableColumn<>("Distribution");
        distribColumn.setMinWidth(200);
        distribColumn.setCellValueFactory(new PropertyValueFactory<>("distribution"));
        distribColumn.setCellFactory(TextFieldTableCell.<ShowModel>forTableColumn());
        distribColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<ShowModel, String> t) -> {
                    ((ShowModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setDistribution(t.getNewValue());
                });

        TableColumn<ShowModel, Date> dateColumn = new TableColumn<>("Date");
        dateColumn.setMinWidth(100);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setCellFactory(TextFieldTableCell.<ShowModel, Date>forTableColumn(new StringConverter<Date>() {
            @Override
            public String toString(Date object) {
                return object.toString();
            }

            @Override
            public Date fromString(String string) {
                return stringToSQlDate(string);
            }
        }));
        dateColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<ShowModel, Date> t) -> {
                    ((ShowModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setDate(t.getNewValue());
                });

        TableColumn<ShowModel, Integer> nrColumn = new TableColumn<>("No. of tickets");
        nrColumn.setMinWidth(100);
        nrColumn.setCellValueFactory(new PropertyValueFactory<>("noOfTickets"));
        nrColumn.setCellFactory(TextFieldTableCell.<ShowModel, Integer>forTableColumn(new IntegerStringConverter()));
        nrColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<ShowModel, Integer> t) -> {
                    ((ShowModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setNoOfTickets(t.getNewValue());
                });


        table.setItems(FXCollections.observableArrayList(showService.findAll()));
        table.getColumns().setAll(idColumn, titleColumn, genreColumn, distribColumn, dateColumn, nrColumn);
    }

    private void setUserFieldsButtons(){
        TextField userField = new TextField();
        userField.setPromptText("username");

        TextField passField = new TextField();
        passField.setPromptText("password");

        this.fieldBox.getChildren().setAll(userField, passField, addBtn);
        this.addBtn.setOnAction(e -> {
            String username = userField.getText();
            String password = userField.getText();
            UserModel u = new UserModel(-1,username, password, false);
            this.userService.addUser(u);
            table.setItems(FXCollections.observableArrayList(this.userService.findAllCashiers()));
        });
        this.editBtn.setOnAction(e -> editUser());
        this.deleteBtn.setOnAction(e -> deleteUser());
    };

    private void setShowFieldsButtons(){
        TextField titleField = new TextField();
        titleField.setPromptText("title");

        TextField genreField = new TextField();
        genreField.setPromptText("genre");

        TextField distribField = new TextField();
        distribField.setPromptText("distribution");

        TextField dateField = new TextField();
        dateField.setPromptText("date");

        //format
        TextField fileField = new TextField();
        fileField.setPromptText("filename");

        Button exportButton = new Button("Export CSV");
        exportButton.setOnAction(e -> {
            String filename = fileField.getText();
            if(filename != "")
                export(filename, FormatType.CSV);
        });

        Button exportXMLButton = new Button("Export XML");
        exportXMLButton.setOnAction(e -> {
            String filename = fileField.getText();
            if(filename != "")
                export(filename, FormatType.XML);
        });



        this.fieldBox.getChildren().setAll(titleField, genreField, distribField, dateField, addBtn, fileField, exportButton, exportXMLButton);
        this.addBtn.setOnAction(e -> {
            String title = titleField.getText();
            String genre = genreField.getText();
            String distrib = distribField.getText();
            java.sql.Date sqlDate = stringToSQlDate(dateField.getText());
            //int nr = Integer.parseInt(nrField.getText());
            ShowModel show = new ShowModel(-1, title, genre, distrib, sqlDate, 0);

            this.showService.addShow(show);
            table.setItems(FXCollections.observableArrayList(showService.findAll()));
        });
        this.editBtn.setOnAction(e -> editShow());
        this.deleteBtn.setOnAction(e -> deleteShow());
    };

    private Date stringToSQlDate(String sDate){
        java.sql.Date sqlDate = null;
        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
            sqlDate = new Date(date.getTime());
        } catch (ParseException ex){
            ex.printStackTrace();
        }
        return sqlDate;
    }

    private void deleteUser(){
        ObservableList<UserModel> selectedUsers = table.getSelectionModel().getSelectedItems();
        if(!selectedUsers.isEmpty()) {
            this.userService.deleteUser(selectedUsers.get(0).getId());
            table.setItems(FXCollections.observableArrayList(this.userService.findAllCashiers()));
        }
    }

    private void editUser(){
        ObservableList<UserModel> selectedUsers = table.getSelectionModel().getSelectedItems();
        if(!selectedUsers.isEmpty()) {
            this.userService.editUser(selectedUsers.get(0));
            table.setItems(FXCollections.observableArrayList(this.userService.findAllCashiers()));
        }
    }

    private void deleteShow(){
        ObservableList<ShowModel> selectedShows = table.getSelectionModel().getSelectedItems();
        if(!selectedShows.isEmpty()){
            this.showService.delete(selectedShows.get(0).getId());
            table.setItems(FXCollections.observableArrayList(showService.findAll()));
        }
    }

    private void editShow(){
        ObservableList<ShowModel> selectedShows = table.getSelectionModel().getSelectedItems();
        if(!selectedShows.isEmpty()){
            this.showService.edit(selectedShows.get(0));
            table.setItems(FXCollections.observableArrayList(showService.findAll()));
        }
    }

    private void export(String filename, FormatType type){
        System.out.println(filename);
        ObservableList<ShowModel> selectedShows = table.getSelectionModel().getSelectedItems();
        if(!selectedShows.isEmpty())
            this.showService.exportSoldTickets(selectedShows.get(0), type, filename);
    }

}
