package com.horace.smarthome.controller;

import com.horace.smarthome.sensor.FireSensor;
import com.horace.smarthome.sensor.HumiditySensor;
import com.horace.smarthome.sensor.SecuritySensor;
import com.horace.smarthome.sensor.TemperatureSensor;

/**
 * Room controller. Handles one room sensors.
 *
 */
public class RoomController {
	private String roomName;
	private FireSensor fireSensor;
	private HumiditySensor humiditySensor;
	private SecuritySensor securitySensor;
	private TemperatureSensor temperatureSensor;

	/**
	 * Constructor.
	 * 
	 * @param roomName Name of the room.
	 */
	public RoomController(String roomName) {
		this.roomName = roomName;
	}

	/**
	 * Method to add fire sensor.
	 * 
	 * @param isActivated Is sensor activated.
	 */
	public void addFireSensor(boolean isActivated) {
		fireSensor = new FireSensor(isActivated);
	}

	/**
	 * Method to add humidity sensor.
	 * 
	 * @param humidity currently set humidity.
	 */
	public void addHumiditySensor(int humidity) {
		humiditySensor = new HumiditySensor(humidity);
	}

	/**
	 * Method to add security sensor.
	 * 
	 * @param isActivated Is security sensor activated.
	 */
	public void addsecuritySensor(boolean isActivated) {
		securitySensor = new SecuritySensor(isActivated);
	}

	/**
	 * Method to add temperature sensor.
	 * 
	 * @param temperature Currently set temperature.
	 */
	public void addTemperatureSensor(int temperature) {
		temperatureSensor = new TemperatureSensor(temperature);
	}

	/**
	 * Method to get this room name.
	 * 
	 * @return Room name.
	 */
	public String getRoomName() {
		return roomName;
	}

	/**
	 * Method to check if fire sensor exists.
	 * 
	 * @return True if fire sensor exists.
	 */
	public boolean fireSensorExists() {
		return fireSensor != null;
	}

	/**
	 * Method to check if humidity sensor exists.
	 * 
	 * @return True if humidity sensor exists.
	 */
	public boolean humiditySensorExists() {
		return humiditySensor != null;
	}

	/**
	 * Method to check if security sensor exists.
	 * 
	 * @return True if security sensor exists.
	 */
	public boolean securitySensorExists() {
		return securitySensor != null;
	}

	/**
	 * Method to check if temperature sensor exists.
	 * 
	 * @return True if temperature sensor exists.
	 */
	public boolean temperatureSensorExists() {
		return temperatureSensor != null;
	}

	/**
	 * Method to get currently set temperature.
	 * 
	 * @return Currently set temperature.
	 */
	public int getCurrentTemperature() {
		return temperatureSensor.getTemperature();
	}

	/**
	 * Method to get sensor value of temperature.
	 * 
	 * @return temperature sensor value.
	 */
	public int getSensorTemperature() {
		return temperatureSensor.getSensorTemperature();
	}

	/**
	 * Method to set currently set temperature.
	 * 
	 * @param temperature
	 */
	public void setTemperature(int temperature) {
		temperatureSensor.setTemperature(temperature);
	}

	/**
	 * Method to get currently set humidity.
	 * 
	 * @return
	 */
	public int getCurrentHumidity() {
		return humiditySensor.getHumidity();
	}

	/**
	 * Method to get humidity sensor value.
	 * 
	 * @return Humidity sensor value.
	 */
	public int getSensorHumidity() {
		return humiditySensor.getSensorHumidity();
	}

	/**
	 * Method to set currently set humidity.
	 * 
	 * @param humidity
	 */
	public void setHumidity(int humidity) {
		humiditySensor.setHumidity(humidity);
	}

	/**
	 * Method to get if fire sensor is activated.
	 * 
	 * @return True if fire sensor is activated.
	 */
	public boolean getFireIsActivated() {
		return fireSensor.isActivated();
	}

	/**
	 * Method to get if fire sensor is alarming.
	 * 
	 * @return True if fire sensor is alarming.
	 */
	public boolean getFireIsAlarming() {
		return fireSensor.isAlarming();
	}

	/**
	 * Method to set fire sensor ON/OFF.
	 * 
	 * @param isActivated
	 */
	public void setFireActivated(boolean isActivated) {
		fireSensor.setActivated(isActivated);
	}

	/**
	 * Method to get if security sensor if activated or not.
	 * 
	 * @return True if security sensor is activated.
	 */
	public boolean getSecurityIsActivated() {
		return securitySensor.isActivated();
	}

	/**
	 * Method to get if security sensor is alrming or not.
	 * 
	 * @return True if security sensor is alarming.
	 */
	public boolean getSecurityIsAlarming() {
		return securitySensor.isAlarming();
	}

	/**
	 * Method to set security sensor ON/OFF.
	 * 
	 * @param isActivated
	 */
	public void setSecurityActivated(boolean isActivated) {
		securitySensor.setActivated(isActivated);
	}

}
