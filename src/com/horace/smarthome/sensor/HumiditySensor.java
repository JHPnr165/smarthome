package com.horace.smarthome.sensor;

public class HumiditySensor {
	
	private int wantedHumidity, sensorHumidity;

	/**
	 * Constructor.
	 * 
	 * @param humidity Humidity value that user wants.
	 */
	public HumiditySensor(int humidity) {
		this.wantedHumidity = humidity;
	}

	/**
	 * Method sets humidity level that user wants.
	 * 
	 * @param humidity Humidity level that user wants.
	 */
	public void setHumidity(int humidity) {
		this.wantedHumidity = humidity;
	}

	/**
	 * Method to get wanted humidity level that is saved.
	 * 
	 * @return Returns wanted humidity level.
	 */
	public int getHumidity() {
		return wantedHumidity;
	}

	/**
	 * Method to get humidity sensor value.
	 * 
	 * @return Returns sensor humidity level.
	 */
	public int getSensorHumidity() {
		return sensorHumidity;
	}

}
