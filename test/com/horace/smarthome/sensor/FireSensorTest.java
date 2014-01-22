package com.horace.smarthome.sensor;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class FireSensorTest {
	FireSensor fireSensor;

	@Before
	public void setUp() throws Exception {
		fireSensor = new FireSensor(true);
	}

	@Test
	public void testIsActivated() {
		assertEquals(true, fireSensor.isActivated());
	}

	@Test
	public void testSetActivated() {
		fireSensor.setActivated(false);
		assertEquals(false, fireSensor.isActivated());
	}

	@Test
	public void testIsAlarming() {
		assertEquals(false, fireSensor.isAlarming());
	}

	@Test
	public void testMute() {
		fireSensor.mute();
		assertEquals(false, fireSensor.isAlarming());
	}
	
}
