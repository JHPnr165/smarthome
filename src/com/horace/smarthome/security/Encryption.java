package com.horace.smarthome.security;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Class for encryption. Here you can encrypt and decrypt data.
 *
 */
public class Encryption {

	/**
	 * Method to encrypt data.
	 * 
	 * @param toEncrypt String to encrypt.
	 * @param passPhrase String of "password".
	 * @return Encrypted data.
	 */
	public byte[] encrypt(String toEncrypt, String passPhrase) {
		byte[] byteArrayToReturn = null;
		Cipher cipherAES = null;
		SecretKeySpec key = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA");
			digest.update(passPhrase.getBytes());
			key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
			cipherAES = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipherAES.init(Cipher.ENCRYPT_MODE, key);
			byteArrayToReturn = cipherAES.doFinal(toEncrypt.getBytes());
		} catch (Exception e) {

		}
		return byteArrayToReturn;
	}

	/**
	 * Method to decrypt data. 
	 * 
	 * @param toDeCrypt byte[] to decrypt.
	 * @param passPhrase String of "password".
	 * @return Decrypted string.
	 */
	public String deCrypt(byte[] toDeCrypt, String passPhrase) {
		String deCryptedString = null;
		Cipher cipherAES = null;
		SecretKeySpec key = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA");
			digest.update(passPhrase.getBytes());
			key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
			cipherAES = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipherAES.init(Cipher.DECRYPT_MODE, key);
			deCryptedString = new String(cipherAES.doFinal(toDeCrypt));
		} catch (Exception e) {

		}
		return deCryptedString;
	}
	
}
