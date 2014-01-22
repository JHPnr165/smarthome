package com.horace.smarthome.sensor;

public class SecuritySensor {
	
	private boolean isAlarming = false, isActivated;

	/**
	 * Constructor.
	 * 
	 * @param isActivated If security sensor is turned ON.
	 */
	public SecuritySensor(boolean isActivated) {
		this.isActivated = isActivated;
	}

	/**
	 * Method to turn security sensor ON or OFF.
	 * 
	 * @param isActivated True if security sensor is turned ON.
	 */
	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	/**
	 * Method to get is security sensor alarming or not.
	 * 
	 * @return True if security sensor is alarming.
	 */
	public boolean isAlarming() {
		return isAlarming;
	}

	/**
	 * Method to get is security sensor turned ON of OFF.
	 * 
	 * @param isActivated True if security sensor is turned ON.
	 */
	public boolean isActivated() {
		return isActivated;
	}
	
}
