package com.caybren.passwordmanager;

import java.security.SecureRandom;

public final class CryptoUtils {
	private static final long ITERATIONS = 600000;
	private static final int KEY_LENGTH = 256; //  length of the derived AES key in bits.
	private static final int SALT_LENGTH = 16; // NIST recommends salts of at least 128 bits for PBKDF2. 16 bytes is the standard size
 	private static final int IV_LENGTH = 12; // AES-GCM specifically, 12 bytes (96 bits) is the recommended size
	private static final int TAG_LENGTH = 128; // length of the authentication tag in bits. "checksum"  GCM attaches to verify the ciphertext is untampered

	
	private CryptoUtils() {}
	
	/* ENSURES GENERATESALT WORKS
	public static void main(String[] args) {
	    byte[] salt = generateSalt();
	    System.out.println("Salt length: " + salt.length);
	    System.out.println("Salt: " + java.util.Base64.getEncoder().encodeToString(salt));
	}
	*/
	
	public static byte[] generateSalt() {
		byte[] saltBytes = new byte[SALT_LENGTH];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(saltBytes);
		return saltBytes;
	}
}
