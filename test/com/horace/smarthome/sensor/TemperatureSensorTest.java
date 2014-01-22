package com.horace.smarthome.sensor;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TemperatureSensorTest {
	TemperatureSensor temperatureSensor;

	@Before
	public void setUp() throws Exception {
		temperatureSensor = new TemperatureSensor(20);
	}

	@Test
	public void testGetTemperature() {
		assertEquals(20, temperatureSensor.getTemperature());
	}

	@Test
	public void testSetTemperature() {
		temperatureSensor.setTemperature(10);
		assertEquals(10, temperatureSensor.getTemperature());
	}

	@Test
	public void testGetSensorTemperature() {
		assertEquals(0, temperatureSensor.getSensorTemperature());
	}
	
}
