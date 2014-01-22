package com.horace.smarthome.security;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EncryptionTest {
	Encryption crypt;

	@Before
	public void setUp() throws Exception {
		crypt = new Encryption();
	}

	@Test
	public void testDeCrypt() {
		String testString = "väike test";
		String stringToCompare = crypt.deCrypt(crypt.encrypt(testString, "smarthome"), "smarthome");
		assertEquals(testString, stringToCompare);
	}
	
}
