package com.caybren.passwordmanager;

import java.security.SecureRandom;

public final class PasswordGen {
	private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+[]{}";
    
    private static final SecureRandom RNG = new SecureRandom();
    
    
    private PasswordGen() {}	
    
    public static String generate(int length, boolean includeSymbols) { 
    	String pool = UPPER + LOWER + DIGITS;
        if (includeSymbols) {
            pool = pool + SYMBOLS;
        }
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            int index = RNG.nextInt(pool.length()); //random index within string pool
            password.append(pool.charAt(index)); // adds character from that index
        }
        return password.toString();
    }
    /*TEST
    public static void main(String[] args) {
        System.out.println("16 with symbols:    " + generate(16, true));
        System.out.println("16 without symbols: " + generate(16, false));
        System.out.println("8 with symbols:     " + generate(8, true));
        System.out.println("32 without symbols: " + generate(32, false));
    }
	*/
}

