package com.caybren.passwordmanager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import javax.crypto.SecretKey;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Vault {
	private final ObservableList<VaultEntry> entries; //entry list
    private final File vaultFile;
    private final File saltFile;
    private final SecretKey key;

    public Vault(File vaultFile, File saltFile, SecretKey key) { //stores
        this.vaultFile = vaultFile; 
        this.saltFile = saltFile;
        this.key = key;
        this.entries = FXCollections.observableArrayList();
    }
    
    public ObservableList<VaultEntry> getEntries() {
        return entries;
    }
    
    public void add(VaultEntry entry) {
        entries.add(entry);
    }

    public void remove(VaultEntry entry) {
        entries.remove(entry);
    }
    
    public void save() throws GeneralSecurityException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); // Creates an in-memory byte buffer; empty bucket sitting in RAM.
        ObjectOutputStream oos = new ObjectOutputStream(baos); //Wraps the byte buffer in an ObjectOutputStream
        oos.writeObject(new ArrayList<>(entries)); // create ArrayList from ObservableList, passes to write Object to serialize -> bytes written to baos
        oos.close(); //closes stream
        byte[] plainBytes = baos.toByteArray(); // Converts the buffer into a plain byte[]. This is the serialized entries as raw bytes

        byte[] encryptedBytes = CryptoUtils.encrypt(key, plainBytes); //encrypts plainBytes

        Files.write(vaultFile.toPath(), encryptedBytes); // Writes the encrypted bytes to vault.dat on disk.
    }
    /* TEST TO SEE IF FILES SAVES AND THE SIZE OF FILE
    public static void main(String[] args) throws Exception {
        byte[] salt = CryptoUtils.generateSalt();
        SecretKey key = CryptoUtils.deriveKey("testpassword", salt);
        
        File vaultFile = new File("test-vault.dat");
        File saltFile = new File("test-vault.salt");
        
        Vault vault = new Vault(vaultFile, saltFile, key);
        vault.add(new VaultEntry("github.com", "fryidae", "hunter2", "personal"));
        vault.add(new VaultEntry("gmail.com", "caybrenc", "abc123", ""));
        
        vault.save();
        System.out.println("Saved. File size: " + vaultFile.length() + " bytes");
    }
*/
    
    @SuppressWarnings("unchecked") //note to compiler
    public void load() throws GeneralSecurityException, IOException, ClassNotFoundException {
        if (!vaultFile.exists()) {
            return;
        }

        byte[] encryptedBytes = Files.readAllBytes(vaultFile.toPath()); //reads the entire file into a byte[]
        byte[] plainBytes = CryptoUtils.decrypt(key, encryptedBytes); 
        //Deserialization
        ByteArrayInputStream bais = new ByteArrayInputStream(plainBytes);  // counterpart to ByteArrayOutputStream
        ObjectInputStream ois = new ObjectInputStream(bais); // bais provides raw bytes, ois interprets them as Java objects. 
        ArrayList<VaultEntry> loaded = (ArrayList<VaultEntry>) ois.readObject(); //It reads the serialized bytes and reconstructs the original object
        ois.close(); //close stream

        entries.clear();
        entries.addAll(loaded);
    }
    
}
