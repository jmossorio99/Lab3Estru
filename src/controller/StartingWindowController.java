package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;

public class StartingWindowController {

	@FXML
	private TextField initialDateTextField;
	@FXML
	private TextField finalDateTextField;
	@FXML
	private HBox togglesHBox;
	@FXML
	private ToggleButton US30Toggle;
	@FXML
	private ToggleButton USSPX500Toggle;
	@FXML
	private ToggleButton WTIToggle;
	@FXML
	private ToggleButton BTCUSDToggle;
	@FXML
	private ToggleButton EURUSDToggle;
	@FXML
	private ToggleButton GBPCADToggle;
	@FXML
	private ToggleButton USDJPYToggle;
	@FXML
	private ToggleButton XAUUSDToggle;
	@FXML
	private Label textResultLbl;
	@FXML
	private Label resultLbl;

	@FXML
	void highestPriceBtnPressed(ActionEvent event) {

	}

	@FXML
	void lowestPriceBtnPressed(ActionEvent event) {

	}

	@FXML
	void showGraphBtnPressed(ActionEvent event) {

	}

	@FXML
	void singleHighestGrowthBtnPressed(ActionEvent event) {

	}

	@FXML
	void stockAboveValueBtnPressed(ActionEvent event) {

	}

	@FXML
	void threeGreatestGrowthBtnPressed(ActionEvent event) {

	}

}
