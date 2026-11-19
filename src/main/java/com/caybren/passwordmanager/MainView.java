package com.caybren.passwordmanager;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainView {

    private final Stage stage;
    private final AppController controller;

    private LoginView loginView;
    private VaultView vaultView;
    private PauseTransition autoLockTimer;
    

    public MainView(Stage stage) {
        this.stage = stage;
        this.controller = new AppController(this);

        this.stage.setTitle("Password Manager");
        controller.start();
        
        this.stage.setOnCloseRequest(e -> {
            // Tells controller to save and logout
        });
    }

    public void showLogin() {
    	if (autoLockTimer != null) {
    	    autoLockTimer.stop();
    	}
        loginView = new LoginView();
        loginView.getLoginButton().setOnAction(e -> {
            String password = loginView.getPassword();
            if (password == null || password.isEmpty()) {
                loginView.setError("Enter a password");
                return;
            }
            boolean success = controller.login(password);
            if (!success) {
                loginView.setError("Wrong password");
                loginView.clear();
            }
        });

        Scene loginScene = new Scene(loginView, 400, 250);
        loginScene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        stage.setScene(loginScene);
        stage.show();
    }

    public void showVault() {
        vaultView = new VaultView(controller.getVault());

        vaultView.getAddButton().setOnAction(e -> {
            controller.addEntry();
            refreshVaultTable();
        });

        vaultView.getEditButton().setOnAction(e -> {
            VaultEntry selected = vaultView.getSelectedEntry();
            controller.editEntry(selected);
            refreshVaultTable();
        });

        vaultView.getDeleteButton().setOnAction(e -> {
            VaultEntry selected = vaultView.getSelectedEntry();
            controller.deleteEntry(selected);
        });

     // 1. Set up the timer for 5 minutes
        autoLockTimer = new PauseTransition(Duration.minutes(5));

        // 2. What happens when time runs out
        autoLockTimer.setOnFinished(e -> controller.logout());

        // 3. Create the scene
        Scene scene = new Scene(vaultView, 700, 400);
        
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());

        // 4. Reset the timer whenever there is ANY mouse or keyboard activity
        scene.addEventFilter(MouseEvent.ANY, e -> autoLockTimer.playFromStart());
        scene.addEventFilter(KeyEvent.ANY, e -> autoLockTimer.playFromStart());

        // 5. Start the timer and show the scene
        autoLockTimer.playFromStart();
        stage.setScene(scene);
        stage.show();
    }

    public void setStatus(String message) {
        if (vaultView != null) {
            vaultView.setStatus(message);
        }
    }

    private void refreshVaultTable() {
        if (vaultView != null) {
            vaultView.getTable().refresh();
        }
    }
}