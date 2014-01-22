package com.horace.smarthome.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.horace.smarthome.ui.SmartHome;

public class ScreensControllerTest {
	ScreensController screensController;

	@Before
	public void setUp() throws Exception {
		screensController = new ScreensController();
	}

	@Test
	public void testSetScreenElseClause() {
		screensController.loadScreen(SmartHome.LOG_SCREEN, SmartHome.LOG_SCREEN_FXML);
		screensController.loadScreen(SmartHome.MAIN_SCREEN, SmartHome.MAIN_SCREEN_FXML);
		screensController.loadScreen(SmartHome.PASSWORD_SCREEN, SmartHome.PASSWORD_SCREEN_FXML);
		screensController.setScreen(SmartHome.MAIN_SCREEN);
		assertNotNull(screensController.getChildren().get(0));
	}

	@Test
	public void testSetScreen() {
		screensController.loadScreen(SmartHome.LOG_SCREEN, SmartHome.LOG_SCREEN_FXML);
		screensController.loadScreen(SmartHome.MAIN_SCREEN, SmartHome.MAIN_SCREEN_FXML);
		screensController.loadScreen(SmartHome.PASSWORD_SCREEN, SmartHome.PASSWORD_SCREEN_FXML);
		screensController.setScreen(SmartHome.LOG_SCREEN);
		screensController.setScreen(SmartHome.MAIN_SCREEN);
		assertNotNull(screensController.getChildren().get(0));
	}

}
