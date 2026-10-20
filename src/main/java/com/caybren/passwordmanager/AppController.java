package com.caybren.passwordmanager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.util.Optional;

import javax.crypto.SecretKey;

public class AppController {

    private final MainView mainView;
    private final File vaultDir;
    private final File vaultFile;
    private final File saltFile;

    private Vault vault;
    private SecretKey currentKey;

    public AppController(MainView mainView) {
        this.mainView = mainView;

        String appSupport = System.getProperty("user.home") + "/Library/Application Support";
        this.vaultDir = new File(appSupport, "PasswordManager");
        this.vaultFile = new File(vaultDir, "vault.dat");
        this.saltFile = new File(vaultDir, "vault.salt");
    }

    private void ensureVaultDirectoryExists() throws IOException {
        if (!vaultDir.exists()) {
            if (!vaultDir.mkdirs()) {
                throw new IOException("Could not create vault directory: " + vaultDir.getAbsolutePath());
            }
        }
    }

    public void start() {
        try {
            ensureVaultDirectoryExists();
        } catch (IOException e) {
            System.err.println("Could not create vault directory: " + e.getMessage());
        }
        mainView.showLogin();
    }

    public boolean login(String password) {
        try {
            byte[] salt;
            if (saltFile.exists()) {
                salt = Files.readAllBytes(saltFile.toPath());
            } else {
                salt = CryptoUtils.generateSalt();
                Files.write(saltFile.toPath(), salt);
            }

            currentKey = CryptoUtils.deriveKey(password, salt);
            vault = new Vault(vaultFile, saltFile, currentKey);
            vault.load();

            mainView.showVault();
            return true;

        } catch (GeneralSecurityException | IOException | ClassNotFoundException e) {
            System.err.println("Login failed: " + e.getMessage());
            return false;
        }
    }
    
    public void addEntry() {
        EntryDialog dialog = new EntryDialog(null);
        Optional<VaultEntry> result = dialog.showAndWait();
        result.ifPresent(entry -> {
            vault.add(entry);
            saveVault();
            mainView.setStatus("Entry added");
        });
    }
    
    public void editEntry(VaultEntry existing) {
        if (existing == null) {
            mainView.setStatus("Select an entry to edit");
            return;
        }
        EntryDialog dialog = new EntryDialog(existing);
        Optional<VaultEntry> result = dialog.showAndWait();
        result.ifPresent(updated -> {
            existing.setSite(updated.getSite());
            existing.setUsername(updated.getUsername());
            existing.setPassword(updated.getPassword());
            existing.setNotes(updated.getNotes());
            saveVault();
            mainView.setStatus("Entry updated");
        });
    }
    
    public void deleteEntry(VaultEntry existing) {
        if (existing == null) {
            mainView.setStatus("Select an entry to delete");
            return;
        }
        vault.remove(existing);
        saveVault();
        mainView.setStatus("Entry deleted");
    }
    
    private void saveVault() {
        try {
            vault.save();
        } catch (GeneralSecurityException | IOException e) {
            System.err.println("Save failed: " + e.getMessage());
            mainView.setStatus("Save failed");
        }
    }
    
    public void logout() {
        saveVault();
        vault = null;
        currentKey = null;
        mainView.showLogin();
    }
    
    public Vault getVault() {
        return vault;
    }
}