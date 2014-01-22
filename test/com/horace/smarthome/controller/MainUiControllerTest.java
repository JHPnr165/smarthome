package com.horace.smarthome.controller;

import static org.junit.Assert.*;

import javafx.fxml.FXMLLoader;

import org.junit.Before;
import org.junit.Test;

public class MainUiControllerTest {
	FXMLLoader loader;
	String url = "/com/horace/smarthome/ui/MainUi.fxml";
	MainUiController mainUiController;
	MainController mainController;
	RoomController roomController;

	@Before
	public void setUp() throws Exception {
		loader = new FXMLLoader(getClass().getResource(url));
		loader.load();
		mainUiController = loader.getController();
		mainController = new MainController(mainUiController);
		mainController.createRoomControllers();
		mainUiController.setMainController(mainController);
	}

	@Test
	public void testSetMainController() {
		assertNotNull(mainUiController.mainController);
	}

	@Test
	public void testSetLogUiController() {
		mainUiController.setLogUiController(new LogUiController());
		assertNotNull(mainUiController.logUiController);
	}

	@Test
	public void testCheckSaved() {
		mainUiController.checkSaved();
		assertEquals("",mainUiController.isSavedLabel.getText());
	}

	@Test
	public void testGetCurrentTemperature() {
		assertEquals(20, mainUiController.getCurrentTemperature());
	}

	@Test
	public void testGetCurrentHumidity() {
		assertEquals(50, mainUiController.getCurrentHumidity());
	}

	@Test
	public void testInitialize() {
		mainUiController.initialize();
		String isSavedLabel = mainUiController.isSavedLabel.getText();
		assertEquals("", isSavedLabel);
	}

	@Test
	public void testUpdateSensorValues() {
		mainUiController.fireAlarmButton.setText("Turn ON");
		mainUiController.updateSensorValues();
		String stringToCompare = mainUiController.fireIsActiveLabel.getText();
		assertEquals("ON", stringToCompare);
	}

	@Test
	public void testUpdateDatabase() {
		mainUiController.humidityChooser.setValue(45);
		mainUiController.temperatureChooser.setValue(18);
		mainUiController.fireAlarmButton.setText("Turn ON");
		mainUiController.securityAlarmButton.setText("Turn OFF");
		mainUiController.checkSaved();
		mainUiController.updateLogFiles();
		mainUiController.checkSaved();
		String stringToCompare = mainUiController.isSavedLabel.getText();
		assertEquals("", stringToCompare);
	}
	
}
