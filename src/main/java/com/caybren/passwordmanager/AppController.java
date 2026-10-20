package com.caybren.passwordmanager;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

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
}