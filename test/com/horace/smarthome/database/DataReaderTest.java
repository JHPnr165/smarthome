package com.horace.smarthome.database;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.horace.smarthome.security.Encryption;

public class DataReaderTest {
	DataReader reader;
	Encryption encryption;

	@Before
	public void setUp() throws Exception {
		reader = new DataReader();
		encryption = new Encryption();
	}

	@Test
	public void testGetRoomsData() {
		String string1 = encryption.deCrypt(reader.getRoomsData(), "smarthome");
		boolean equals = string1.startsWith("{\"rooms\":[{");
		assertEquals(true, equals);
	}

	@Test
	public void testGetLog() {
		String log = reader.getLog("test.log");
		assertEquals("testike\n", log);
	}

	@Test
	public void testGetLogFail() {
		String log = reader.getLog("te.log");
		assertEquals("", log);
	}

}
