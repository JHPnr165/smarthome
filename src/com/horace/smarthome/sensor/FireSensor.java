package com.horace.smarthome.sensor;

public class FireSensor {
	
	private boolean isActivated, isAlarming = false;

	/**
	 * Constructor.
	 * 
	 * @param isActivated is fire sensor turned ON or OFF.
	 */
	public FireSensor(boolean isActivated) {
		this.isActivated = isActivated;
	}

	/**
	 * Method to get is fire sensor turned ON or OFF.
	 * 
	 * @return True if fire sensor is turned ON.
	 */
	public boolean isActivated() {
		return isActivated;
	}

	/**
	 * Method to set fire sensor ON or OFF.
	 * 
	 * @param isActivated Set fire sensor ON or OFF.
	 */
	public void setActivated(boolean isActivated) {
		
		if(!isActivated) {
			isAlarming = false;
		}
		
		this.isActivated = isActivated;
	}

	/**
	 * Method to silence fire sensor.
	 */
	public void mute() {
		isAlarming = false;
	}

	/**
	 * Method to get is fire sensor alarming or not.
	 * 
	 * @return True if fire sensor is alarming.
	 */
	public boolean isAlarming() {
		return isAlarming;
	}
	
}
