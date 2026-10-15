package com.caybren.passwordmanager;

import java.io.Serializable;

public class VaultEntry implements Serializable {
	private String site;
    private String username;
    private String password;
    private String notes;
    
    public VaultEntry() {}
    
    public VaultEntry(String site, String username, String password, String notes) {
    	this.site = site;
        this.username = username;
        this.password = password;
        this.notes = notes;
    	
    }

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
    /*TEST
	public static void main(String[] args) {
	    VaultEntry entry = new VaultEntry("github.com", "fryidae", "password", "personal");
	    System.out.println("Site:     " + entry.getSite());
	    System.out.println("Username: " + entry.getUsername());
	    System.out.println("Password: " + entry.getPassword());
	    System.out.println("Notes:    " + entry.getNotes());
	}
	*/
	
}
