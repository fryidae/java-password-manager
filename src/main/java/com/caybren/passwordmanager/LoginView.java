package com.caybren.passwordmanager;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class LoginView extends BorderPane {

    private PasswordField passwordField;
    private Button loginButton;
    private Label errorLabel;

    public LoginView() {
        buildUI();
    }

    private void buildUI() {
    	setPadding(new Insets(20)); //adds space around the inside of the BorderPane's edges.

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setAlignment(Pos.CENTER);

        setCenter(form);

        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setAlignment(Pos.CENTER);

        setBottom(errorLabel);
    }
    
    public String getPassword() {
        return passwordField.getText();
    }

    public void setError(String message) {
        errorLabel.setText(message);
    }

    public void clear() {
        passwordField.clear();
        errorLabel.setText("");
    }
}