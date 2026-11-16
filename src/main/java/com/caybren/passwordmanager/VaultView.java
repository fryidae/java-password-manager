package com.caybren.passwordmanager;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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
    
    private TextField searchField;

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
        
        searchField = new TextField();
        searchField.setPromptText("Search...");
        
        toolBar.getItems().addAll(addButton, editButton, deleteButton, new Separator(), searchField);
        topArea.getChildren().add(toolBar);
        
        table = new TableView<>();

        siteColumn = new TableColumn<>("Site");
        siteColumn.setCellValueFactory(new PropertyValueFactory<>("site"));

        usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        passwordColumn.setCellFactory(column -> new TableCell<VaultEntry, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                // If the cell is empty, show nothing
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Otherwise, dots
                    setText("••••••••");
                }
            }
        });

        notesColumn = new TableColumn<>("Notes");
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));

        table.getColumns().addAll(siteColumn, usernameColumn, passwordColumn, notesColumn);
        
        
        
        
     // 1. Wrap the observable list in a FilteredList
        FilteredList<VaultEntry> filteredData = new FilteredList<>(vault.getEntries(), p -> true);

        // 2. Listen for changes in the search field
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(entry -> {
                // If search field is empty, show all entries
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare site name to the search text (ignoring uppercase/lowercase)
                String lowerCaseFilter = newValue.toLowerCase();
                if (entry.getSite() != null && entry.getSite().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        // 3. Wrap the FilteredList in a SortedList (so clicking column headers still sorts)
        SortedList<VaultEntry> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Give the table the sorted, filtered data
        table.setItems(sortedData);
        
        
        
        
        setCenter(table);
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