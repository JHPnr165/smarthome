package com.horace.smarthome.controller;

import static org.junit.Assert.*;

import java.util.Date;

import javafx.fxml.FXMLLoader;

import org.junit.Before;
import org.junit.Test;

import com.horace.smarthome.ui.SmartHome;

public class LogUiControllerTest {
	LogUiController logUiController;
	ScreensController screensController;
	FXMLLoader loader;

	@Before
	public void setUp() throws Exception {
		loader = new FXMLLoader(getClass().getResource(SmartHome.LOG_SCREEN_FXML));
		loader.load();
		logUiController = loader.getController();
		screensController = new ScreensController();
	}

	@Test
	public void testGetLogData() {
		String[] logItems = logUiController.getLogData("testroom_temperature.log");
		long time = Long.parseLong("1383962812526");
		Date date = new Date(time);
		String testItem = "[" + date + "] : 3";
		assertEquals(testItem, logItems[0]);
	}

	@Test
	public void testUpdateLog() {
		logUiController.updateLog("testroom", "temperature");
		boolean activated = logUiController.radioAsList.isDisabled();
		assertEquals(activated, false);
	}

	@Test
	public void testSetScreenParent() {
		logUiController.setScreenParent(screensController);
		assertNotNull(logUiController.screensController);
	}
	
}
