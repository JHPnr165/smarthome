package com.horace.smarthome.controller;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.horace.smarthome.database.DataReader;
import com.horace.smarthome.security.Encryption;

/**
 * Main controller. This controller will handle RoomController's.
 * When initializing it you must give MainUiController as argument.
 *
 */
public class MainController {
	private ArrayList<RoomController> roomControllers = new ArrayList<RoomController>();
	MainUiController mainUiController;

	/**
	 * Method to get RoomControllers.
	 * 
	 * @return
	 */
	public ArrayList<RoomController> getRoomControllers() { 
		return roomControllers; 
	}

	/**
	 * Constructor.
	 * 
	 * @param controller MainUiController to interact with.
	 */
	public MainController(MainUiController controller) {
		this.mainUiController = controller;
		createRoomControllers();
	}

	/**
	 * Method to set this in MainUiController.
	 */
	public void setMainControllerInUI() {
		mainUiController.setMainController(this);
	}

	/**
	 * Method to get RoomController with given name.
	 * 
	 * @param roomName Room name in RoomController.
	 * @return RoomController with given name.
	 */
	public RoomController getRoomController(String roomName) {
		RoomController roomControllerToReturn = null;
		
		for(RoomController roomController : roomControllers) {
			
			if(roomController.getRoomName().equals(roomName)) {
				roomControllerToReturn = roomController;
				break;
			}
			
		}
		
		return roomControllerToReturn;
	}

	/**
	 * Method to get room names that are loaded.
	 * 
	 * @return names of the rooms in ArrayList.
	 */
	public ArrayList<String> getRoomNames() {
		ArrayList<String> roomNames = new ArrayList<String>();

		for(RoomController roomController : roomControllers) {
			roomNames.add(roomController.getRoomName());
		}

		return roomNames;
	}

	/**
	 * Method to create RoomController objects.
	 */
	public void createRoomControllers() {
		JSONObject roomsData = new JSONObject(readRoomsDataFromFile());
		try {
			JSONArray roomsJsonArray = roomsData.getJSONArray("rooms");
			
			for(int i = 0; i < roomsJsonArray.length(); i++) {
				JSONObject room = roomsJsonArray.getJSONObject(i);
				createRoomController(room);
			}
			
		} catch(Exception e) {}	 //File doesn't exist or in wrong format
	}

	/**
	 * Method that create RoomController. createRoomControllers() is
	 * using that method.
	 * 
	 * @param room JSon of the room.
	 */
	private void createRoomController(JSONObject room) {
		try{
			RoomController roomController = new RoomController(
					room.getString("room"));
			try{
				roomController.addFireSensor(room.getBoolean("fire"));
			} catch(Exception ex) {} //Fire alarm doesn't exist
			try{
				roomController.addsecuritySensor(room.getBoolean("security"));
			} catch(Exception ex) {} //Security alarm doesn't exist
			try{
				roomController.addTemperatureSensor(room.getInt("temperature"));
			} catch(Exception ex) {} //Temperature sensor doesn't exist
			try{
				roomController.addHumiditySensor(room.getInt("humidity"));
			} catch(Exception ex) {} //Humidity sensor doesn't exist
			roomControllers.add(roomController);
		} catch(Exception e) {} //Wrong format in file
	}

	/**
	 * Method to get rooms data from file.
	 * 
	 * @return
	 */
	private String readRoomsDataFromFile() {
		DataReader reader = new DataReader();
		Encryption crypter = new Encryption();
		String roomsDataString = crypter.deCrypt(reader.getRoomsData(), "smarthome");
		return roomsDataString;
	}
	
}
