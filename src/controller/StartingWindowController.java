package controller;

import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
	private ToggleButton toggled;
	private ToggleButton prevToggled;
	private boolean alreadyToggled = false;

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
	
	//preview del metodo------
	public void fillTreeWithIntervals(String inf,String sup) {
		
	}

	@FXML
	void togglePressed(ActionEvent event) {

		toggled = (ToggleButton) event.getSource();
		if (alreadyToggled) {
			for (Node node : togglesHBox.getChildren()) {

				if (node == prevToggled) {
					prevToggled.setSelected(false);
				}

			}
			prevToggled = toggled;
		} else {
			alreadyToggled = true;
			prevToggled = toggled;
		}

	}

	private boolean verifySelections() {

		if (!initialDateTextField.getText().isEmpty() && !finalDateTextField.getText().isEmpty() && correctDateFormat()
				&& toggled != null) {
			return true;
		}
		return false;

	}

	private boolean correctDateFormat() {

		if (Pattern.matches("\\d\\d?/\\d\\d?/\\d{4}\\s\\d\\d?:\\d\\d?", initialDateTextField.getText())
				&& Pattern.matches("\\d\\d?/\\d\\d?/\\d{4}\\s\\d\\d?:\\d\\d?", finalDateTextField.getText())) {
			return true;
		}
		return false;

	}

}
