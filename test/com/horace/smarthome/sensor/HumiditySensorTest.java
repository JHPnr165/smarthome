package com.horace.smarthome.sensor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HumiditySensorTest {
	HumiditySensor sensor;

	@Before
	public void setUp() throws Exception {
		sensor = new HumiditySensor(50);
	}

	@Test
	public void testGetHumidity() {
		assertEquals(50, sensor.getHumidity());
	}

	@Test
	public void testSetHumidity() {
		sensor.setHumidity(40);
		assertEquals(40, sensor.getHumidity());
	}

	@Test
	public void testGetSensorHumidity() {
		assertEquals(0, sensor.getSensorHumidity());
	}
	
}
