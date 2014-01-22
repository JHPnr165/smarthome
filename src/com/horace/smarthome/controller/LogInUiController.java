package com.horace.smarthome.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.horace.smarthome.ui.SmartHome;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class LogInUiController implements ControlledScreen {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView logInIcon;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label wrongPasswordField;

    public ScreensController screensController;

    private String password = "smarthome";

    /**
     * Method is called when login icon is clicked.
     * Switches screen to login screen.
     * 
     * @param event
     */
    @FXML
    void onLogInButtonClicked(MouseEvent event) {
    	if(passwordField.getText().equals(password)) {
    		wrongPasswordField.setVisible(false);
    		passwordField.setText("");
    		screensController.setScreen(SmartHome.MAIN_SCREEN);
    	} else {
    		wrongPasswordField.setVisible(true);
    		passwordField.setText("");
    	}
    }

    /**
     * Methos is called when login icon is pressed.
     * Will reduce login icon size.
     * 
     * @param event
     */
    @FXML
    void onLogInButtonPressed(MouseEvent event) {
    	logInIcon.setFitHeight(58);
    	logInIcon.setFitWidth(80);
    }

    /**
     * Method is called when login icon is released after pressing
     * login icon. Gives normal size back to login icon.
     * 
     * @param event
     */
    @FXML
    void onLogInButtonReleased(MouseEvent event) {
    	logInIcon.setFitHeight(66);
    	logInIcon.setFitWidth(88);
    }

    /**
     * Method is automatically executed by JavaFX after main UI is created from MainUi.fxml.
     */
    @FXML
    void initialize() {

    }

    /**
     * Method to set ScreensController.
     */
	@Override
	public void setScreenParent(ScreensController screensController) {
		this.screensController = screensController;
	}

}