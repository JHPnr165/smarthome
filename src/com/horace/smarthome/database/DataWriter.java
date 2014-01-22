package com.horace.smarthome.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

import com.horace.smarthome.controller.RoomController;
import com.horace.smarthome.security.Encryption;

/**
 * Writer class. Here you can write data to files.
 *
 */
public class DataWriter {

	/**
	 * Method to write data file. Takes in byte[] because data files
	 * are encrypted. Overwrites the file.
	 * 
	 * @param toWrite byte[] to write.
	 * @param fileName File name to write to.
	 */
	public void writeDatabase(byte[] toWrite, String fileName) {
		FileOutputStream writer;
		try {
			File file = new File("data/" + fileName);
			writer = new FileOutputStream(file);
			writer.write(toWrite);
			writer.close();
		} catch (Exception e) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "couldn't save new values!");
		}
	}

	/**
	 * Method to write data to file. Takes in ArrayList of RoomController's
	 * that must be saved and file name to write to. It'll use Encryption class
	 * to encrypt the data and then calls writeDatabase(byte[] bytes, StringfileName).
	 * 
	 * @param roomControllers ArrayList of RoomController's.
	 * @param fileName Name of the file to write to.
	 */
	public void writeDatabase(ArrayList<RoomController> roomControllers, String fileName) {
		JSONArray roomsArray = new JSONArray();
		
		for(RoomController roomController : roomControllers) {
			JSONObject room = new JSONObject();
			room.put("room", roomController.getRoomName());
			
			if(roomController.temperatureSensorExists()) {
				room.put("temperature", roomController.getCurrentTemperature());
			}
			
			if(roomController.humiditySensorExists()) {
				room.put("humidity", roomController.getCurrentHumidity());
			}
			
			if(roomController.securitySensorExists()) {
				room.put("security", roomController.getSecurityIsActivated());
			}
			
			if(roomController.fireSensorExists()) {
				room.put("fire", roomController.getFireIsActivated());
			}
			
			roomsArray.put(room);
		}
		
		JSONObject roomsJson = new JSONObject();
		roomsJson.put("rooms", roomsArray);
		writeDatabase(new Encryption().encrypt(roomsJson.toString(), "smarthome"), fileName);
	}

	/**
	 * Method to update log file. It appends new values to the end
	 * of the given file.
	 * 
	 * @param toWrite String to append to file.
	 * @param fileName Name of the file to append the string.
	 */
	public void writeToLog(String toWrite, String fileName) {
		FileWriter writer;
		try {
			fileName = "log/" + fileName;
			writer = new FileWriter(fileName, true);
			writer.write(toWrite);
			writer.write("\n");
			writer.close();
		} catch(Exception e) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "couldn't save to log!");
		}
	}
	
}