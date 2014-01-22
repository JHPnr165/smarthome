package com.horace.smarthome.controller;

import java.util.HashMap;

import com.horace.smarthome.ui.SmartHome;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * Screens controller class. Handles different screens.
 * With this class you control which screen is displayed.
 *
 */
public class ScreensController extends StackPane {
	private HashMap<String, Node> screens = new HashMap<>();
	private MainUiController mainUiController;
	private LogUiController logUiController;
	private double firstWidth;
	private double firstHeight;
	private boolean isMainControllerSet = false;

	/**
	 * Method to add new screen to HashMap.
	 * 
	 * @param screenName Name of the screen.
	 * @param screen Screen Node.
	 */
	public void addScreen(String screenName, Node screen) {
		screens.put(screenName, screen);
	}

	/**
	 * Method to load new screen.
	 * 
	 * @param screenName Screen name.
	 * @param resource Location of the FXML file.
	 */
	public void loadScreen(String screenName, String resource) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
			Parent parent = (Parent) loader.load();
			ControlledScreen myScreenController = ((ControlledScreen) loader.getController());
			myScreenController.setScreenParent(this);
			addScreen(screenName, parent);
			
			if(screenName.equals(SmartHome.MAIN_SCREEN)) {
				mainUiController = loader.getController();
			}
			
			if(screenName.equals(SmartHome.LOG_SCREEN)) {
				logUiController = loader.getController();
			}
			
		} catch(Exception e) {
			System.exit(0);
		}
	}

	/**
	 * Method to set window new position.
	 * Window will be always in the center of the screen.
	 * If not used then different size windows will be in different places.
	 */
	public void setPosition() {
		double widthDifference = (firstWidth - this.prefWidth(USE_PREF_SIZE)) / 2;
		double heightDifference = (firstHeight - this.prefHeight(USE_PREF_SIZE)) / 2;
		double newPositionX = this.getScene().getWindow().getX() + widthDifference;
		double newPositionY = this.getScene().getWindow().getY() + heightDifference;
		this.getScene().getWindow().setHeight(this.prefHeight(USE_PREF_SIZE));
		this.getScene().getWindow().setWidth(this.prefWidth(USE_PREF_SIZE));
		this.getScene().getWindow().setX(newPositionX);
		this.getScene().getWindow().setY(newPositionY);
	}

	/**
	 * Method to save current windows size. 
	 */
	public void setCurrentWindowSize() {
		firstWidth = this.getWidth();
		firstHeight = this.getHeight();
	}

	/**
	 * Method to change screen that is displayed.
	 * 
	 * @param screenName Name of the screen to display.
	 */
	public void setScreen(final String screenName) {
		
		if(screens.get(screenName) != null) {
			final DoubleProperty opacity = opacityProperty();
			setCurrentWindowSize();
			
			if(!getChildren().isEmpty()) {

				@SuppressWarnings({ "unchecked", "rawtypes" })
				Timeline fade = new Timeline(
						new KeyFrame(Duration.ZERO,
								new KeyValue(opacity, 1.0)),
						new KeyFrame(new Duration(500),
								new EventHandler() {
	
							@Override
							public void handle(Event t) {
								getChildren().remove(0);
								getChildren().add(0, screens.get(screenName));
								setPosition();
								Timeline fadeIn = new Timeline(
										new KeyFrame(Duration.ZERO,
												new KeyValue(opacity, 0.0)),
										new KeyFrame(new Duration(1500),
												new KeyValue(opacity, 1.0)));
								fadeIn.play();
							}
						},
						new KeyValue(opacity, 0.0)));
				fade.play();
				
			} else {
				setOpacity(0.0); 
				getChildren().add(0, screens.get(screenName));
				Timeline fadeIn = new Timeline( 
						new KeyFrame(Duration.ZERO, 
								new KeyValue(opacity, 0.0)), 
								new KeyFrame(new Duration(2500), 
								new KeyValue(opacity, 1.0))); 
				fadeIn.play();
			}
			
			if(screenName.equals(SmartHome.MAIN_SCREEN) && !isMainControllerSet) {
				MainController mainController = new MainController(mainUiController);
				mainController.setMainControllerInUI();
				mainUiController.setLogUiController(logUiController);
				isMainControllerSet = true;
			}
			
		} else {
			System.exit(0);
		}
	}
}
