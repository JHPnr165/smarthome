package com.horace.smarthome.controller;

import static org.junit.Assert.*;
import javafx.fxml.FXMLLoader;

import org.junit.Before;
import org.junit.Test;

public class LogInUiControllerTest {
	LogInUiController logInUiController;
	ScreensController screensController;
	String url = "/com/horace/smarthome/ui/LogInUi.fxml";
	FXMLLoader loader;

	@Before
	public void setUp() throws Exception {
		loader = new FXMLLoader(getClass().getResource(url));
		loader.load();
		logInUiController = loader.getController();
		screensController = new ScreensController();
	}

	@Test
	public void testSetScreenParent() {
		logInUiController.setScreenParent(screensController);
		assertNotNull(logInUiController.screensController);
	}

}
