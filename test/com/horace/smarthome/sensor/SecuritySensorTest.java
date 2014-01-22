package com.horace.smarthome.sensor;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SecuritySensorTest {
	SecuritySensor securitySensor;

	@Before
	public void setUp() throws Exception {
		securitySensor = new SecuritySensor(true);
	}

	@Test
	public void testIsActivated() {
		assertEquals(true, securitySensor.isActivated());
	}

	@Test
	public void testSetActivated() {
		securitySensor.setActivated(false);
		assertEquals(false, securitySensor.isActivated());
	}

	@Test
	public void testIsAlarming() {
		assertEquals(false, securitySensor.isAlarming());
	}
	
}
