package com.caybren.passwordmanager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class EntryDialog extends Dialog<VaultEntry> {
    private TextField siteField;
    private TextField usernameField;
    private PasswordField passwordField;
    private TextArea notesArea;
    private Button generateButton;
    private VaultEntry existing;

    public EntryDialog(VaultEntry existing) { 
    	this.existing = existing;
    	buildUI();	
    }

    private void buildUI() { 
        setTitle("Entry");
        setHeaderText("Enter password details");

        getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        siteField = new TextField();
        siteField.setPromptText("e.g. github.com");

        usernameField = new TextField();
        usernameField.setPromptText("e.g. fryidae");

        passwordField = new PasswordField();
        passwordField.setPromptText("password");

        generateButton = new Button("Generate");

        notesArea = new TextArea();
        notesArea.setPromptText("Optional notes");
        notesArea.setPrefRowCount(3);

        grid.add(new Label("Site:"), 0, 0);
        grid.add(siteField, 1, 0);
        grid.add(new Label("Username:"), 0, 1);
        grid.add(usernameField, 1, 1);
        grid.add(new Label("Password:"), 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(generateButton, 2, 2);
        grid.add(new Label("Notes:"), 0, 3);
        grid.add(notesArea, 1, 3, 2, 1);

        getDialogPane().setContent(grid);
        
        generateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                passwordField.setText(PasswordGen.generate(16, true));
            }
        });

        if (existing != null) {
            siteField.setText(existing.getSite());
            usernameField.setText(existing.getUsername());
            passwordField.setText(existing.getPassword());
            notesArea.setText(existing.getNotes());
        }

        setResultConverter(button -> {
            if (button == ButtonType.OK) {
                return new VaultEntry(siteField.getText(),usernameField.getText(),passwordField.getText(),notesArea.getText());
            }
            return null;
        });
        
        
        
    }
    

}
