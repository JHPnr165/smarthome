package com.horace.smarthome.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.horace.smarthome.database.DataReader;
import com.horace.smarthome.ui.SmartHome;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Controller for Log UI.
 *
 */
public class LogUiController implements ControlledScreen {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView backIcon;

    @FXML
    private LineChart<String, Integer> chart;

    @FXML
    private NumberAxis numberAxis;

    @FXML
    private ListView<String> list;

    @FXML
    private RadioButton radioAsChart;

    @FXML
    RadioButton radioAsList;

    @FXML
    private ToggleGroup showAs;

    ScreensController screensController;

    private String[] listOfLogItems;

    /**
     * Method is called when back icon is clicked.
     * Will set screen back to mainUI.
     * 
     * @param event
     */
    @FXML
    void onBackIconClicked(MouseEvent event) {
    	screensController.setScreen(SmartHome.MAIN_SCREEN);
    }

    /**
     * Method is called when mouse enters back icon.
     * Increases back icon size.
     * 
     * @param event
     */
    @FXML
    void onBackIconEntered(MouseEvent event) {
    	backIcon.setFitHeight(57);
    	backIcon.setFitWidth(75);
    }

    /**
     * Method is called when mouse exits back icon.
     * Decreases back icon size.
     * 
     * @param event
     */
    @FXML
    void onBackIconExited(MouseEvent event) {
    	backIcon.setFitHeight(51);
    	backIcon.setFitWidth(68);
    }

    /**
     * Method is called when show as chart button is clicked.
     * Chart will be set visible and list view will be invisible.
     * 
     * @param event
     */
    @FXML
    void onShowAsChartButtonClicked(ActionEvent event) {
    	list.setVisible(false);
    	chart.setVisible(true);
    }

    /**
     * Method is called when show as list button is clicked.
     * Chart will be set invisible and list view will be visible.
     * 
     * @param event
     */
    @FXML
    void onShowAsListButtonClicked(ActionEvent event) {
    	list.setVisible(true);
    	chart.setVisible(false);
    }

    /**
     * Method to get data which will be shown in chart and list view.
     * 
     * @param fileName File name to get data from.
     * @return
     */
    String[] getLogData(String fileName) {
    	DataReader reader = new DataReader();
    	String logItems = reader.getLog(fileName);
    	listOfLogItems = logItems.split("\n");
    	String[] listViewLogItems = {""};

    	if(!listOfLogItems[0].equals("")) {
    		listViewLogItems = new String[listOfLogItems.length];
    		for(int i = 0; i < listOfLogItems.length; i++) {
        		String log = new String(listOfLogItems[i]);
        		Date date = new Date(Long.parseLong(log.substring(0, log.indexOf(":"))));
        		String value = log.substring(log.indexOf(":") + 1);
        		log = "[" + date + "] : " + value;
        		listViewLogItems[i] = log;
        	}
    	}
    	
    	return listViewLogItems;
    }

    /**
     * Method that updates log items in list view and calls method to update
     * chart if sensor is not fire sensor nor security sensor.
     * 
     * @param roomName Name of the room to get data.
     * @param sensor Name of the sensor to get data.
     */
    public void updateLog(String roomName, String sensor) {
    	String fileName = roomName + "_" + sensor + ".log";
    	ObservableList<String> observableListOfLogItems = 
    			FXCollections.observableArrayList(getLogData(fileName));
    	list.setItems(observableListOfLogItems);
    	if(sensor.equals("fire") || sensor.equals("security")) {
    		radioAsChart.setDisable(true);
    		radioAsList.setDisable(true);
    	} else {
    		radioAsChart.setDisable(false);
    		radioAsList.setDisable(false);
    		updateChart(roomName, sensor);
    	}
    	list.setVisible(true);
    	chart.setVisible(false);
    }

    /**
     * Method to update chart data.
     * 
     * @param roomName Name of the room to update data.
     * @param sensor Name of the sensor to update data.
     */
    @SuppressWarnings("unchecked")
	private void updateChart(String roomName, String sensor) {
    	chart.getData().clear();
    	@SuppressWarnings("rawtypes")
		XYChart.Series chartSeries = new XYChart.Series();
    	radioAsList.setSelected(true);
    	if(!listOfLogItems[0].equals("")) {
    		chart.setTitle(roomName + " - " + sensor);
    		for(String logItem : listOfLogItems) {
        		chartSeries.getData().add(makeChartData(logItem));
        	}
        	chart.getData().add(chartSeries);
    	}
    }

    /**
     * Method to make XYChart.Data type of object.
     * Takes in String which is line from log file.
     * Format of the String is "long_value_of_date":"value"
     * 
     * @param logItemString
     * @return
     */
    @SuppressWarnings("rawtypes")
	private XYChart.Data makeChartData(String logItemString) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    	Date date = new Date(Long.parseLong(
				logItemString.substring(0, logItemString.indexOf(":"))));
		String dateString = dateFormat.format(date);
		int value = Integer.parseInt(
				logItemString.substring(logItemString.indexOf(":") + 1));
		@SuppressWarnings("unchecked")
		XYChart.Data chartData = new XYChart.Data(dateString, value);
    	return chartData;
    }

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
