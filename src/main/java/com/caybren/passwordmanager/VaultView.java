package com.caybren.passwordmanager;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;

public class VaultView extends BorderPane {

    private Vault vault;

    private TableView<VaultEntry> table;
    private TableColumn<VaultEntry, String> siteColumn;
    private TableColumn<VaultEntry, String> usernameColumn;
    private TableColumn<VaultEntry, String> passwordColumn;
    private TableColumn<VaultEntry, String> notesColumn;

    private MenuBar menuBar;
    private ToolBar toolBar;
    private Button addButton;
    private Button editButton;
    private Button deleteButton;
    
    private VBox topArea;

    private Label statusLabel;

    public VaultView(Vault vault) {
        this.vault = vault;
        buildUI();
    }

    private void buildUI() {
        setPadding(new Insets(10));

        topArea = new VBox();
        setTop(topArea);
        
        menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.getItems().addAll(saveItem, exitItem);

        Menu helpMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About");
        helpMenu.getItems().add(aboutItem);

        menuBar.getMenus().addAll(fileMenu, helpMenu);
        topArea.getChildren().add(menuBar);
        
        
        toolBar = new ToolBar();
        addButton = new Button("Add");
        editButton = new Button("Edit");
        deleteButton = new Button("Delete");
        toolBar.getItems().addAll(addButton, editButton, deleteButton);
        topArea.getChildren().add(toolBar);
        
        table = new TableView<>();

        siteColumn = new TableColumn<>("Site");
        siteColumn.setCellValueFactory(new PropertyValueFactory<>("site"));

        usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        notesColumn = new TableColumn<>("Notes");
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));

        table.getColumns().addAll(siteColumn, usernameColumn, passwordColumn, notesColumn);
        
        table.setItems(vault.getEntries());   // give the table data
        setCenter(table);                     // put the table in the layout

        statusLabel = new Label("Ready");
        setBottom(statusLabel);

    }
    
    public Button getAddButton() {
        return addButton;
    }

    public Button getEditButton() {
        return editButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public VaultEntry getSelectedEntry() {
        return table.getSelectionModel().getSelectedItem();
    }

    public void setStatus(String message) {
        statusLabel.setText(message);
    }
    
    public TableView<VaultEntry> getTable() {
        return table;
    }
    
    
}