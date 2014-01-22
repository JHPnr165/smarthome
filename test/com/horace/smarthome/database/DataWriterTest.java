package com.horace.smarthome.database;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;

import org.junit.Before;
import org.junit.Test;

import com.horace.smarthome.controller.MainController;
import com.horace.smarthome.controller.MainUiController;
import com.horace.smarthome.controller.RoomController;
import com.horace.smarthome.security.Encryption;

public class DataWriterTest {
	DataWriter writer;
	DataReader reader;
	Encryption encryption;
	ArrayList<RoomController> rooms;
	RoomController roomController;
	FXMLLoader loader;
	String url = "/com/horace/smarthome/ui/MainUi.fxml";
	MainController mainController;
	MainUiController mainUiController;

	@Before
	public void setUp() throws Exception {
		writer = new DataWriter();
		reader = new DataReader();
		encryption = new Encryption();
		rooms = new ArrayList<RoomController>();
		roomController = new RoomController("kitchen");
		roomController.addFireSensor(true);
		roomController.addsecuritySensor(false);
		roomController.addHumiditySensor(50);
		roomController.addTemperatureSensor(20);
		rooms.add(roomController);
		roomController = new RoomController("bedroom1");
		roomController.addFireSensor(true);
		roomController.addsecuritySensor(false);
		roomController.addHumiditySensor(50);
		roomController.addTemperatureSensor(21);
		rooms.add(roomController);
		roomController = new RoomController("bedroom2");
		roomController.addFireSensor(true);
		roomController.addsecuritySensor(false);
		roomController.addHumiditySensor(50);
		roomController.addTemperatureSensor(21);
		rooms.add(roomController);
		roomController = new RoomController("garage");
		roomController.addFireSensor(true);
		roomController.addsecuritySensor(false);
		roomController.addHumiditySensor(50);
		roomController.addTemperatureSensor(10);
		rooms.add(roomController);
		roomController = new RoomController("hall");
		roomController.addFireSensor(true);
		roomController.addsecuritySensor(false);
		roomController.addHumiditySensor(50);
		roomController.addTemperatureSensor(20);
		rooms.add(roomController);
	}

	@Test
	public void testUpdateDatabase() {
		writer.writeDatabase(rooms, "data.txt");
		loader = new FXMLLoader(getClass().getResource(url));
		try {
			loader.load();
		} catch (IOException e) {}
		mainUiController = loader.getController();
		mainController = new MainController(mainUiController);
		mainController.createRoomControllers();
		
		assertNotNull(mainController.getRoomController("kitchen"));
	}

	@Test
	public void testWriteToLog() {
		writer.writeToLog("\ntestike", "test2.log");
		String stringToCompare = reader.getLog("test2.log");
		boolean isSame = stringToCompare.endsWith("testike\n");
		assertEquals(true, isSame);
	}

}
