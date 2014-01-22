package com.horace.smarthome.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.horace.smarthome.ui.SmartHome;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Password UI controller. It's handling events in password UI.
 *
 */
public class PasswordUiController implements ControlledScreen {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView closeButton;

    @FXML
    private ImageView logInButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label wrongPasswordLabel;

    private String password = "smarthome";

    public ScreensController screensController;

    /**
     * Method is called when close icon is clicked.
     * Will shut down program.
     * 
     * @param event
     */
    @FXML
    void onCloseButtonClicked(MouseEvent event) {
    	System.exit(0);
    }

    /**
     * Method is called when mouse is pressed on close icon.
     * Will decrease close icon size.
     * 
     * @param event
     */
    @FXML
    void onCloseButtonPressed(MouseEvent event) {
    	closeButton.setFitHeight(48);
    	closeButton.setFitWidth(48);
    }

    /**
     * Method is called when mouse is released on close icon.
     * Will increase close icon size to it's normal size.
     * 
     * @param event
     */
    @FXML
    void onCloseButtonReleased(MouseEvent event) {
    	closeButton.setFitHeight(58);
    	closeButton.setFitWidth(58);
    }

    /**
     * Method is called when login button is clicked.
     * If password is correct then changes screen to main screen.
     * 
     * @param event
     */
    @FXML
    void onLogInButtonClicked(MouseEvent event) {
    	
    	if(passwordField.getText().equals(password)) {
    		screensController.setScreen(SmartHome.MAIN_SCREEN);
    	} else {
    		wrongPasswordLabel.setVisible(true);
    		passwordField.setText("");
    	}
    	
    }

    /**
     * Method is called when login button is pressed.
     * Will decrease login button size.
     * 
     * @param event
     */
    @FXML
    void onLogInButtonPressed(MouseEvent event) {
    	logInButton.setFitHeight(58);
    	logInButton.setFitWidth(80);
    }

    /**
     * Method is called when mouse is released on login button.
     * Will increase login button size to it's normal size.
     * 
     * @param event
     */
    @FXML
    void onLogInButtonReleased(MouseEvent event) {
    	logInButton.setFitHeight(66);
    	logInButton.setFitWidth(88);
    }

    /**
     * FXML method which is triggered when object is created.
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