package com.horace.smarthome.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Reader class. Here you can read data from files.
 *
 */
public class DataReader {
	
	private final String FILE_NAME = "data/data.txt";

	public DataReader() {}

	/**
	 * Method to read rooms data from file.
	 * It will read from data.txt file.
	 * It'll return byte[] because the file is encrypted.
	 * 
	 * @return
	 */
	public byte[] getRoomsData() {
		byte[] bytesToReturn = null;
		long length;
		FileInputStream reader;
		try {
			File file = new File(FILE_NAME);
			length = file.length();
			bytesToReturn = new byte[(int)length];
			reader = new FileInputStream(FILE_NAME);
			reader.read(bytesToReturn);
			reader.close();
		} catch (Exception e) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "couldn't read data!");
		}
		return bytesToReturn;
	}

	/**
	 * Method to read log file. It'll return String of the content
	 * where different line are separated by "\n".
	 * 
	 * @param fileName Name of the file to read from.
	 * @return
	 */
	public String getLog(String fileName) {
		try {
			fileName = "log/" + fileName;
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String logString = "";
			String line = "";
			
			while ((line = reader.readLine()) != null) {
				logString += line + "\n";
			}
			
			reader.close();
			return logString;
		} catch(Exception e) {
			return "";
		}
	}
}