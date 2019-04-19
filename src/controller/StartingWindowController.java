package controller;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.AVLTree;
import model.HighestGrowthFinder;
import model.LimitReader;
import model.RBTree;

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
	AVLTree<String> avl;
	RBTree<String> rbt;

	@FXML
	void highestPriceBtnPressed(ActionEvent event) {

		String num = "";
		String resultText = "";
		if (verifySelections()) {

			LimitReader reader = new LimitReader(initialDateTextField.getText(), finalDateTextField.getText(),
					toggled.getText() + " prices.txt");
			if (toggled.getText().contains("#")) {
				try {
					avl = reader.getAlvOnLimit();
					num = avl.getMax();
					resultText = "The highest price of the stock market " + toggled.getText() + " is: ";
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					rbt = reader.getRbOnLimit();
					num = rbt.getMaxValue();
					resultText = "The highest price of the currency market " + toggled.getText() + " is: ";
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			textResultLbl.setText(resultText);
			resultLbl.setText(num);

		} else {
			JOptionPane.showMessageDialog(null, "Please verify the date format and the market selection", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	@FXML
	void lowestPriceBtnPressed(ActionEvent event) {

		String num = "";
		String resultText = "";
		if (verifySelections()) {

			LimitReader reader = new LimitReader(initialDateTextField.getText(), finalDateTextField.getText(),
					toggled.getText() + " prices.txt");
			if (toggled.getText().contains("#")) {
				try {
					avl = reader.getAlvOnLimit();
					num = avl.getMin();
					resultText = "The highest price of the stock market " + toggled.getText() + " is: ";
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					rbt = reader.getRbOnLimit();
					num = rbt.getMinValue();
					resultText = "The highest price of the currency market " + toggled.getText() + " is: ";
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			textResultLbl.setText(resultText);
			resultLbl.setText(num);

		} else {
			JOptionPane.showMessageDialog(null, "Please verify the date format and the market selection", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	@FXML
	void showGraphBtnPressed(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/GraphWindowView.fxml"));

			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage window = new Stage();
			window.setResizable(false);
			window.setScene(scene);
			window.setTitle("Graph View");
			window.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void highestGrowthYearBtnPressed(ActionEvent event) {

		HighestGrowthFinder hgf = new HighestGrowthFinder(toggled.getText() + " prices.txt");
		try {
			textResultLbl.setText("The year with the highest growth for the market " + toggled.getText() + " is: ");
			resultLbl.setText(hgf.findHighestGrowthYear());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void highestGrowthDayBtnPressed(ActionEvent event) {

		HighestGrowthFinder hgf = new HighestGrowthFinder(toggled.getText() + " prices.txt");
		try {
			textResultLbl.setText("The day with the highest growth for the market " + toggled.getText() + " is: ");
			resultLbl.setText(hgf.findHighestGrowthDay());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void highestGrowthMonthBtnPressed(ActionEvent event) {

		HighestGrowthFinder hgf = new HighestGrowthFinder(toggled.getText() + " prices.txt");
		try {
			textResultLbl.setText("The month with the highest growth for the market " + toggled.getText() + " is: ");
			resultLbl.setText(hgf.findHighestGrowthMonth());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void stockAboveValueBtnPressed(ActionEvent event) {

	}

	@FXML
	void threeGreatestGrowthBtnPressed(ActionEvent event) {

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
