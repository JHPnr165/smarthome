package com.horace.smarthome.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RoomControllerTest {
	RoomController roomController;

	@Before
	public void setUp() throws Exception {
		roomController = new RoomController("hall");
	}

	@Test
	public void testRoomController() {
		assertEquals("hall", roomController.getRoomName());
	}

	@Test
	public void testAddFireSensor() {
		roomController.addFireSensor(true);
		assertEquals(true, roomController.fireSensorExists());
	}

	@Test
	public void testAddHumiditySensor() {
		roomController.addHumiditySensor(50);
		assertEquals(true, roomController.humiditySensorExists());
	}

	@Test
	public void testAddsecuritySensor() {
		roomController.addsecuritySensor(true);
		assertEquals(true, roomController.securitySensorExists());
	}

	@Test
	public void testAddTemperatureSensor() {
		roomController.addTemperatureSensor(20);
		assertEquals(true, roomController.temperatureSensorExists());
	}

	@Test
	public void testGetRoomName() {
		assertEquals("hall", roomController.getRoomName());
	}

	@Test
	public void testFireSensorExists() {
		roomController.addFireSensor(true);
		assertEquals(true, roomController.fireSensorExists());
	}

	@Test
	public void testHumiditySensorExists() {
		roomController.addHumiditySensor(50);
		assertEquals(true, roomController.humiditySensorExists());
	}

	@Test
	public void testSecuritySensorExists() {
		roomController.addsecuritySensor(true);
		assertEquals(true, roomController.securitySensorExists());
	}

	@Test
	public void testTemperatureSensorExists() {
		roomController.addTemperatureSensor(20);
		assertEquals(true, roomController.temperatureSensorExists());
	}

	@Test
	public void testGetCurrentTemperature() {
		roomController.addTemperatureSensor(20);
		assertEquals(20, roomController.getCurrentTemperature());
	}

	@Test
	public void testGetSensorTemperature() {
		roomController.addTemperatureSensor(20);
		assertEquals(0, roomController.getSensorTemperature());
	}

	@Test
	public void testSetTemperature() {
		roomController.addTemperatureSensor(20);
		roomController.setTemperature(25);
		assertEquals(25, roomController.getCurrentTemperature());
	}

	@Test
	public void testGetCurrentHumidity() {
		roomController.addHumiditySensor(50);
		assertEquals(50, roomController.getCurrentHumidity());
	}

	@Test
	public void testGetSensorHumidity() {
		roomController.addHumiditySensor(50);
		assertEquals(0, roomController.getSensorHumidity());
	}

	@Test
	public void testSetHumidity() {
		roomController.addHumiditySensor(50);
		roomController.setHumidity(30);
		assertEquals(30, roomController.getCurrentHumidity());
	}

	@Test
	public void testGetFireIsActivated() {
		roomController.addFireSensor(true);
		assertEquals(true, roomController.getFireIsActivated());
	}

	@Test
	public void testGetFireIsAlarming() {
		roomController.addFireSensor(true);
		assertEquals(false, roomController.getFireIsAlarming());
	}

	@Test
	public void testSetFireActivated() {
		roomController.addFireSensor(true);
		roomController.setFireActivated(false);
		assertEquals(false, roomController.getFireIsActivated());
	}

	@Test
	public void testGetSecurityIsActivated() {
		roomController.addsecuritySensor(true);
		assertEquals(true, roomController.getSecurityIsActivated());
	}

	@Test
	public void testGetSecurityIsAlarming() {
		roomController.addsecuritySensor(true);
		assertEquals(false, roomController.getSecurityIsAlarming());
	}

	@Test
	public void testSetSecurityActivated() {
		roomController.addsecuritySensor(true);
		roomController.setSecurityActivated(false);
		assertEquals(false, roomController.getSecurityIsActivated());
	}
	
}
