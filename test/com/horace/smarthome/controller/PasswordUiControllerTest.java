package com.horace.smarthome.controller;

import static org.junit.Assert.*;

import javafx.fxml.FXMLLoader;

import org.junit.Before;
import org.junit.Test;

public class PasswordUiControllerTest {
	PasswordUiController passwordUiController;
	ScreensController screensController;
	String url = "/com/horace/smarthome/ui/PasswordUi.fxml";
	FXMLLoader loader;

	@Before
	public void setUp() throws Exception {
		loader = new FXMLLoader(getClass().getResource(url));
		loader.load();
		passwordUiController = loader.getController();
		screensController = new ScreensController();
	}

	@Test
	public void testSetScreenParent() {
		passwordUiController.setScreenParent(screensController);
		assertNotNull(passwordUiController.screensController);
	}
	
}
