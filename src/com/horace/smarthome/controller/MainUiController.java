package com.horace.smarthome.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.horace.smarthome.database.DataWriter;
import com.horace.smarthome.ui.SmartHome;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class MainUiController implements ControlledScreen {

    @FXML
    private ImageView closeIcon;

    @FXML
    private ImageView saveIcon;

    @FXML
    private ImageView logOutIcon;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label currentHumidityLabel;

    @FXML
    private Label currentTemperatureLabel;

    @FXML
    public Button fireAlarmButton;

    @FXML
    public Label fireIsActiveLabel;

    @FXML
    private Label fireIsAlarmingLabel;

    public Label isSavedLabel;

    @FXML
    public Button securityAlarmButton;

    @FXML
    private Label securityIsActiveLabel;

    @FXML
    private Label securityIsAlarmingLabel;

    @FXML
    private Label sensorHumidityLabel;

    @FXML
    private Label sensorTemperatureLabel;

    @FXML
    private GridPane gridPane;

    @FXML
    private FlowPane roomChoosePane;

    @FXML
    public ComboBox<String> roomChooser;

    @FXML
    public ComboBox<Integer> temperatureChooser;

    @FXML
    public ComboBox<Integer> humidityChooser;

    public MainController mainController;

    private RoomController roomController;

	private ScreensController screensController;

    public LogUiController logUiController;

    /**
     * Method sets main controller, then asks room names from main controller and then calls
     * setRoomChooserValues() method to put the names into room chooser.
     * 
     * @param mainController
     */
    public void setMainController(MainController mainController) {
    	this.mainController = mainController;
    	setRoomChooserValues(mainController.getRoomNames());
    	roomController = mainController.getRoomController(
    			mainController.getRoomNames().get(0));
    	roomChooser.setValue(mainController.getRoomNames().get(0));
    	updateSensorValues();
    }

    /**
     * Method to set LogUiController.
     * 
     * @param logUiController
     */
    public void setLogUiController(LogUiController logUiController) {
    	this.logUiController = logUiController;
    }

    /**
     * Method is called if humidity chooser value has changed.
     * Calls checkSaved() method.
     * 
     * @param event
     */
    @FXML
    private void onHumidityChooserValueChanged(ActionEvent event) {
    	checkSaved();
    }

    /**
     * Method is called if temperature chooser value has changed.
     * Calls checkSaved() method.
     * 
     * @param event
     */
    @FXML
    private void onTemperatureChooserValueChanged(ActionEvent event) {
    	checkSaved();
    }

    /**
     * Method is called if room chooser value has changed.
     * Changes room which values are being shown in GUI.
     * 
     * @param event
     */
    @FXML
    void onRoomChooserValueChanged(ActionEvent event) {
    	roomController = mainController.getRoomController(roomChooser.getValue());
    	updateSensorValues();
    }

    /**
     * Method is called if cursor enters room chooser.
     * Shows list of rooms to choose.
     * 
     * @param event
     */
    @FXML
    void onMouseOverRoomChooser(MouseEvent event) {
    	roomChooser.show();
    }

    /**
     * Method is called if fire button is clicked.
     * Modifies button value and calls checkSaved() method.
     * 
     * @param event
     */
    @FXML
    void onFireAlarmButtonClicked(ActionEvent event) {
    	
    	if(fireAlarmButton.getText().equals("Turn ON")) {
    		fireAlarmButton.setText("Turn OFF");
    	} else {
    		fireAlarmButton.setText("Turn ON");
    	}
    	
    	checkSaved();
    }

    /**
     * Method is called if security button is clicked.
     * Modifies button value and calls checkSaved() method.
     * 
     * @param event
     */
    @FXML
    void onSecurityAlarmButtonClicked(ActionEvent event) {
    	
    	if(securityAlarmButton.getText().equals("Turn ON")) {
    		securityAlarmButton.setText("Turn OFF");
    	} else {
    		securityAlarmButton.setText("Turn ON");
    	}
    	
    	checkSaved();
    }

    /**
     * Method is called if close icon is clicked. Program will exit.
     * 
     * @param event
     */
    @FXML
    void onCloseIconClicked(MouseEvent event) {
    	System.exit(0);
    }

    /**
     * Method is called if cursor enters close icon.
     * Gives other areas an effect of blur.
     * 
     * @param event
     */
    @FXML
    private void onCloseIconEntered(MouseEvent event) {
    	BoxBlur blur = new BoxBlur();
    	blur.setHeight(5);
    	blur.setWidth(5);
    	blur.setIterations(5);
    	gridPane.setEffect(blur);
    	roomChoosePane.setEffect(blur);
    }

    /**
     * Method is called if cursor exits close icon.
     * Takes blur effect off of other areas.
     * 
     * @param event
     */
    @FXML
    private void onCloseIconExited(MouseEvent event) {
    	BoxBlur blur = new BoxBlur();
    	blur.setHeight(0);
    	blur.setWidth(0);
    	blur.setIterations(0);
    	gridPane.setEffect(blur);
    	roomChoosePane.setEffect(blur);
    }

    /**
     * Method is called after mouse button is pressed in close icon.
     * Reduces close icon size.
     * 
     * @param event
     */
    @FXML
    void onCloseIconPressed(MouseEvent event) {
    	closeIcon.setFitHeight(48);
    	closeIcon.setFitWidth(48);
    }

    /**
     * Method is called after mouse button is released after pressing close icon.
     * Gives normal size back to close icon.
     * 
     * @param event
     */
    @FXML
    void onCloseIconReleased(MouseEvent event) {
    	closeIcon.setFitHeight(58);
    	closeIcon.setFitWidth(58);
    }

    /**
     * Method is called after save icon is clicked.
     * Will save changed values of current room.
     * Will call updateLogFiles() method.
     * 
     * @param event
     */
    @FXML
    void onSaveIconClicked(MouseEvent event) {
    	
    	if(!isSaved()) {
    		DataWriter dataWriter = new DataWriter();
    		updateLogFiles();
    		dataWriter.writeDatabase(mainController.getRoomControllers(), "data.txt");
    		isSavedLabel.setText("");
    	} else {
    		
    	}
    	
    }

    /**
     * Method that updates log files. If value has changed then it will
     * add new value to log file.
     */
    void updateLogFiles() {
    	DataWriter writer = new DataWriter();
    	String fileName, toWrite;
    	
    	if(isTemperatureDifferent()) {
    		toWrite = System.currentTimeMillis() + ":" + temperatureChooser.getValue();
    		fileName = roomController.getRoomName() + "_temperature.log";
    		roomController.setTemperature(temperatureChooser.getValue());
    		updateTemperatureValues();
    		writer.writeToLog(toWrite, fileName);
    	}
    	
    	if(isHumidityDifferent()) {
    		toWrite = System.currentTimeMillis() + ":" + humidityChooser.getValue();
    		fileName = roomController.getRoomName() + "_humidity.log";
    		roomController.setHumidity(humidityChooser.getValue());
    		updateHumidityValues();
    		writer.writeToLog(toWrite, fileName);
    	}
    	
    	if(isSecurityDifferent()) {
    		fileName = roomController.getRoomName() + "_security.log";
    		
    		if(securityAlarmButton.getText().endsWith("ON")) {
        		roomController.setSecurityActivated(false);
    		} else {
    			roomController.setSecurityActivated(true);
    		}
    		
    		updateSecurityValues();
    		toWrite = System.currentTimeMillis() + ":" + roomController.getSecurityIsActivated();
    		writer.writeToLog(toWrite, fileName);
    	}
    	
    	if(isFireDifferent()) {
    		fileName = roomController.getRoomName() + "_fire.log";
    		
    		if(fireAlarmButton.getText().endsWith("ON")) {
    			roomController.setFireActivated(false);
    		} else {
    			roomController.setFireActivated(true);
    		}
    		
    		toWrite = System.currentTimeMillis() + ":" + roomController.getFireIsActivated();
    		updateFireValues();
    		writer.writeToLog(toWrite, fileName);
    	}
    	
    }

    /**
     * Method to get if currently set values are saved or not.
     * 
     * @return True if values are saved.
     */
    private boolean isSaved() {
    	
    	if(isTemperatureDifferent() || isHumidityDifferent()
    			|| isSecurityDifferent() || isFireDifferent()) {
    		return false;
    	} else {
    		return true;
    	}
    	
    }

    /**
     * Method is called after cursor enters save icon.
     * Rotates save icon by 180 degrees.
     * 
     * @param event
     */
    @FXML
    void onSaveIconEntered(MouseEvent event) {
    	RotateTransition rotateTransition =
    			new RotateTransition(Duration.millis(220), saveIcon);
    	rotateTransition.setCycleCount(1);
    	rotateTransition.setByAngle(180f);
    	rotateTransition.play();
    }

    /**
     * Method is called when cursor exits save icon.
     * Gives normal rotation back to save icon.
     * 
     * @param event
     */
    @FXML
    void onSaveIconExited(MouseEvent event) {
    	saveIcon.setRotate(0);
    }

    /**
     * Method is called after mouse button is pressed in save icon.
     * Reduces save icon size.
     * 
     * @param event
     */
    @FXML
    void onSaveIconPressed(MouseEvent event) {
    	saveIcon.setFitHeight(55);
    	saveIcon.setFitWidth(55);
    }

    /**
     * Method is called if mouse button is released after pressing save icon.
     * Gives normal size back to save icon.
     * 
     * @param event
     */
    @FXML
    void onSaveIconReleased(MouseEvent event) {
    	saveIcon.setFitHeight(65);
    	saveIcon.setFitWidth(65);
    }

    /**
     * Method is called when log out icon is clicked.
     * Switches screen to log in screen.
     * 
     * @param event
     */
    @FXML
    void onLogOutIconClicked(MouseEvent event) {
    	screensController.setScreen(SmartHome.LOG_IN_SCREEN);
    }

    /**
     * Methos is called when log out icon is pressed.
     * Will reduce log out icon size.
     * 
     * @param event
     */
    @FXML
    void onLogOutIconPressed(MouseEvent event) {
    	logOutIcon.setFitHeight(48);
    	logOutIcon.setFitWidth(48);
    }

    /**
     * Method is called when log out icon is released after pressing
     * log out icon. Gives normal size back to log out icon.
     * 
     * @param event
     */
    @FXML
    void onLogOutIconReleased(MouseEvent event) {
    	logOutIcon.setFitHeight(58);
    	logOutIcon.setFitWidth(58);
    }

    /**
     * Method is called after changing value in GUI (other methods will call this method).
     * If any changed value is different from
     * saved value then it shows text which asks to save values.
     */
    public void checkSaved() {
    	
    	if(isTemperatureDifferent() || isHumidityDifferent()
    			|| isSecurityDifferent() || isFireDifferent()) {
    		isSavedLabel.setText("New values not saved. Please save!");
    	} else {
    		isSavedLabel.setText("");
    	}
    	
    }

    /**
     * Compares fire sensor ON/OFF values from GUI.
     * 
     * @return Returns true if saved value and selected value are different.
     */
    private boolean isFireDifferent() {
    	
    	if(fireAlarmButton.getText().equals("Turn " + fireIsActiveLabel.getText())) {
    		return true;
    	} else {
    		return false;
    	}
    	
    }

    /**
     * Compares security sensor ON/OFF values from GUI.
     * 
     * @return Returns true if saved value and selected value are different.
     */
    private boolean isSecurityDifferent() {
    	
    	if(securityAlarmButton.getText().equals("Turn " + securityIsActiveLabel.getText())) {
    		return true;
    	} else {
    		return false;
    	}
    	
    }

    /**
     * Compares temperature values from GUI. Not selected is considered as not changed.
     * 
     * @return Returns true if saved value and selected value are different.
     */
    private boolean isTemperatureDifferent() {
    	
    	if(temperatureChooser.getValue() == null) {
    		return false;
    	} else if(getCurrentTemperature() == temperatureChooser.getValue()) {
    		return false;
    	} else {
    		return true;
    	}
    	
    }

    /**
     * Compares humidity values from GUI. Not selected is considered as not changed.
     * 
     * @return True if saved value and selected value are different.
     */
    private boolean isHumidityDifferent() {
    	
    	if(humidityChooser.getValue() == null) {
    		return false;
    	} else if(getCurrentHumidity() == humidityChooser.getValue()) {
    		return false;
    	} else {
    		return true;
    	}
    	
    }

    /**
     * Method that sets room names in room chooser. Takes in ArrayList of Strings.
     * 
     * @param roomNames Room names list.
     */
    private void setRoomChooserValues(ArrayList<String> roomNames) {
    	roomChooser.getItems().addAll(roomNames);
    }

    /**
     * Method that updates selected room sensor values.
     */
    public void updateSensorValues() {
    	updateFireValues();
    	updateSecurityValues();
    	updateHumidityValues();
    	updateTemperatureValues();
    }

    /**
     * Method updates selected room fire alarm values.
     */
    private void updateFireValues() {
    	try{
    		
    		if(roomController.getFireIsAlarming()) {
    			fireIsAlarmingLabel.setText("ALARMING!");
    		} else {
    			fireIsAlarmingLabel.setText("Not alarming");
    		}
    		
    		if(roomController.getFireIsActivated()) {
    			fireAlarmButton.setText("Turn OFF");
    			fireIsActiveLabel.setText("ON");
    		} else {
    			fireAlarmButton.setText("Turn ON");
    			fireIsActiveLabel.setText("OFF");
    		}
    		
    		fireAlarmButton.setDisable(false);
    	} catch(Exception e) { //Fire alarm doesn't exist
    		fireIsActiveLabel.setText("-");
    		fireIsAlarmingLabel.setText("-");
    		fireAlarmButton.setDisable(true);
    	}
    }

    /**
     * Method updates selected room security alarm values.
     */
    private void updateSecurityValues() {
    	try{
    		
    		if(roomController.getSecurityIsAlarming()) {
    			securityIsAlarmingLabel.setText("ALARMING!");
    		} else {
    			securityIsAlarmingLabel.setText("Not alarming");
    		}
    		
    		if(roomController.getSecurityIsActivated()) {
    			securityAlarmButton.setText("Turn OFF");
    			securityIsActiveLabel.setText("ON");
    		} else {
    			securityAlarmButton.setText("Turn ON");
    			securityIsActiveLabel.setText("OFF");
    		}
    		
    		securityAlarmButton.setDisable(false);
    	} catch(Exception e) { //Security alarm doesn't exist
    		securityAlarmButton.setDisable(true);
    		securityIsActiveLabel.setText("-");
    		securityIsAlarmingLabel.setText("-");
    	}
    }

    /**
     * Method updates selected room humidity values.
     */
    private void updateHumidityValues() {
    	try{
    		currentHumidityLabel.setText(String.valueOf(roomController.getCurrentHumidity()));
    		sensorHumidityLabel.setText(String.valueOf(roomController.getSensorHumidity()));
    		humidityChooser.setDisable(false);
    	} catch(Exception e) { //Humidity sensor doesn't exist
    		humidityChooser.setDisable(true);
    		currentHumidityLabel.setText("0");
    		sensorHumidityLabel.setText("0");
    	}
    }

    /**
     * Method updates selected room temperature values.
     */
    private void updateTemperatureValues() {
    	try{
    		currentTemperatureLabel.setText(String.valueOf(roomController.getCurrentTemperature()));
    		sensorTemperatureLabel.setText(String.valueOf(roomController.getSensorTemperature()));
    		temperatureChooser.setDisable(false);
    	} catch(Exception e) { //Temperature sensor doesn't exist
    		temperatureChooser.setDisable(true);
    		currentTemperatureLabel.setText("0");
    		sensorTemperatureLabel.setText("0");
    	}
    }

    /**
     * @return Returns saved temperature level of selected room.
     */
    @FXML
    int getCurrentTemperature() {
    	return Integer.parseInt(currentTemperatureLabel.getText());
    }

    /**
     * @return Returns saved humidity level of selected room.
     */
    @FXML
    int getCurrentHumidity() {
    	return Integer.parseInt(currentHumidityLabel.getText());
    }

    /**
     * Method that is called when securityIsActive label is clicked.
     * Will set screen to log view and calls method to update listView
     * and chart.
     * 
     * @param event
     */
    @FXML
    void onSecurityIsActiveLabelClicked(MouseEvent event) {
    	screensController.setScreen(SmartHome.LOG_SCREEN);
    	logUiController.updateLog(roomController.getRoomName(), "security");
    }

    /**
     * Method that is called when fireIsActive label is clicked.
     * Will set screen to log view and calls method to update listView
     * and chart.
     * 
     * @param event
     */
    @FXML
    void onFireIsActiveLabelClicked(MouseEvent event) {
    	screensController.setScreen(SmartHome.LOG_SCREEN);
    	logUiController.updateLog(roomController.getRoomName(), "fire");
    }

    /**
     * Method that is called when currentTemperatureLabel label is clicked.
     * Will set screen to log view and calls method to update listView
     * and chart.
     * 
     * @param event
     */
    @FXML
    void onCurrentTemperatureLabelClicked(MouseEvent event) {
    	screensController.setScreen(SmartHome.LOG_SCREEN);
    	logUiController.updateLog(roomController.getRoomName(), "temperature");
    }

    /**
     * Method that is called when currentHumidityLabel label is clicked.
     * Will set screen to log view and calls method to update listView
     * and chart.
     * 
     * @param event
     */
    @FXML
    void onCurrentHumidityLabelClicked(MouseEvent event) {
    	screensController.setScreen(SmartHome.LOG_SCREEN);
    	logUiController.updateLog(roomController.getRoomName(), "humidity");
    }

    /**
     * Method is automatically executed by JavaFX after main UI is created from MainUi.fxml.
     */
    @FXML
    void initialize() {
    	isSavedLabel = new Label("");
    	isSavedLabel.setTextFill(Color.RED);
    	gridPane.add(isSavedLabel, 3, 6, 3, 1); //span couldn't be done in Scenebuilder therefore it's here
    }

    /**
     * Method to set ScreensController.
     */
	@Override
	public void setScreenParent(ScreensController screensController) {
		this.screensController = screensController;
	}
	
}
