package com.horace.smarthome.sensor;

public class TemperatureSensor {
	
	private int wantedTemperature, sensorTemperature;

	/**
	 * Constructor.
	 * 
	 * @param temperature Temperature that user wants.
	 */
	public TemperatureSensor(int temperature) {
		this.wantedTemperature = temperature;
	}

	/**
	 * Set temperature that user wants.
	 * 
	 * @param temperature Temperature user wants.
	 */
	public void setTemperature(int temperature) {
		this.wantedTemperature = temperature;
	}

	/**
	 * Method to get temperature that user wants and is saved.
	 * 
	 * @return Returns temperature that user wants and is saved.
	 */
	public int getTemperature() {
		return wantedTemperature;
	}

	/**
	 * Method to get temperature sensor value.
	 * 
	 * @return Returns sensor value.
	 */
	public int getSensorTemperature() {
		return sensorTemperature;
	}

}
