package com.horace.smarthome.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class MainControllerTest {
	MainController mainController;
	MainUiController mainUiController;
	RoomController roomController;

	@Before
	public void setUp() throws Exception {
		mainUiController = new MainUiController();
		mainController = new MainController(mainUiController);
	}

	@Test
	public void testGetRoomControllers() {
		ArrayList<RoomController> roomControllers = mainController.getRoomControllers();
		assertEquals(5,roomControllers.size());
	}

	@Test
	public void testGetRoomController() {
		roomController = mainController.getRoomController("hall");
		assertEquals("hall", roomController.getRoomName());
	}

	@Test
	public void testGetRoomNames() {
		ArrayList<String> result = mainController.getRoomNames();
		ArrayList<String> compareTo = new ArrayList<String>();
		compareTo.add("kitchen");
		compareTo.add("bedroom1");
		compareTo.add("bedroom2");
		compareTo.add("garage");
		compareTo.add("hall");
		assertEquals(compareTo, result);
	}
	
}
