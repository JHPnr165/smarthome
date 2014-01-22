package com.horace.smarthome.ui;

import com.horace.smarthome.controller.ScreensController;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Main class.
 *
 */
public class SmartHome extends Application {
	
	public static final String MAIN_SCREEN = "main";
	public static final String MAIN_SCREEN_FXML = "/com/horace/smarthome/ui/MainUi.fxml";
	public static final String PASSWORD_SCREEN = "password";
	public static final String PASSWORD_SCREEN_FXML = "/com/horace/smarthome/ui/PasswordUi.fxml";
	public static final String LOG_SCREEN = "log";
	public static final String LOG_SCREEN_FXML = "/com/horace/smarthome/ui/LogUi.fxml";
	public static final String LOG_IN_SCREEN = "login";
	public static final String LOG_IN_SCREEN_FXML = "/com/horace/smarthome/ui/LogInUi.fxml";

	/**
	 * Main method to start the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		ScreensController screensController = new ScreensController();
		screensController.loadScreen(LOG_SCREEN, LOG_SCREEN_FXML);
		screensController.loadScreen(MAIN_SCREEN, MAIN_SCREEN_FXML);
		screensController.loadScreen(LOG_IN_SCREEN, LOG_IN_SCREEN_FXML);
		screensController.loadScreen(PASSWORD_SCREEN, PASSWORD_SCREEN_FXML);
		screensController.setScreen(PASSWORD_SCREEN);

		Group root = new Group();
		root.getChildren().addAll(screensController);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();		
	}

}
