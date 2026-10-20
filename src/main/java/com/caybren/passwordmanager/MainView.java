package com.caybren.passwordmanager;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView {

    private final Stage stage;
    private final AppController controller;

    private LoginView loginView;
    private VaultView vaultView;

    public MainView(Stage stage) {
        this.stage = stage;
        this.controller = new AppController(this);

        this.stage.setTitle("Password Manager");
        controller.start();
    }

    public void showLogin() {
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

        stage.setScene(new Scene(loginView, 400, 250));
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

        stage.setScene(new Scene(vaultView, 700, 400));
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