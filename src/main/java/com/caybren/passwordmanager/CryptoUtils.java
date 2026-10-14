package com.caybren.passwordmanager;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.ByteBuffer;


public final class CryptoUtils {
	private static final int ITERATIONS = 600000;
	private static final int KEY_LENGTH = 256; //  length of the derived AES key in bits.
	private static final int SALT_LENGTH = 16; // NIST recommends salts of at least 128 bits for PBKDF2. 16 bytes is the standard size
 	private static final int IV_LENGTH = 12; // AES-GCM specifically, 12 bytes (96 bits) is the recommended size
	private static final int TAG_LENGTH = 128; // length of the authentication tag in bits. "checksum"  GCM attaches to verify the ciphertext is untampered
	private static final SecureRandom RNG = new SecureRandom();

	
	private CryptoUtils() {}
	
	//10/13/26
	public static byte[] generateSalt() {
		byte[] saltBytes = new byte[SALT_LENGTH]; // empty array of 16 bytes
		//SecureRandom secureRandomSalt = new SecureRandom(); // secure RNG
		//secureRandomSalt.nextBytes(saltBytes); // fill array with random bytes
		RNG.nextBytes(saltBytes); //earlier 2 lines combined to one
		return saltBytes;
	}
	
	//10/14/26
	public static SecretKey deriveKey(String password, byte[] salt)
			  throws NoSuchAlgorithmException, InvalidKeySpecException {
				//Asks Java for the object that knows how to run PBKDF2 with HMAC-SHA256.
			    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			    // description of what i want the key to be
			    // Run PBKDF2 → get raw bytes
			    PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
			    //Wrap those bytes as an AES key and returns it
			    SecretKey secretKey = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
			    return secretKey;
			}
	
	public static byte[] encrypt(SecretKey key, byte[] data) throws GeneralSecurityException {
        byte[] iv = new byte[IV_LENGTH];  // generates Fresh IV
        RNG.nextBytes(iv);				  //
        Cipher c = Cipher.getInstance("AES/GCM/NoPadding"); // cipher object that runs AES in GCM mode.
        c.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(TAG_LENGTH, iv)); // tells the cipher to encrypt
        // takes your plain text and produces
        // Cipher text and Authentication tag
        byte[] ct = c.doFinal(data);
        return ByteBuffer.allocate(iv.length + ct.length).put(iv).put(ct).array(); // builds a new array with the IV prepended to the ciphertext
    }

    public static byte[] decrypt(SecretKey key, byte[] message) throws GeneralSecurityException {
        Cipher c = Cipher.getInstance("AES/GCM/NoPadding");
        // grabs bytes 0–11 of message and uses them as the IV.
        c.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(TAG_LENGTH, message, 0, IV_LENGTH)); 
        return c.doFinal(message, IV_LENGTH, message.length - IV_LENGTH);
    }
	
	

	
	
	
	
	
	
	
	
	
	
	
	
}


