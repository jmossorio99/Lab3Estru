package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class GraphWindowController implements Initializable {

	@FXML
	private CategoryAxis x;

	@FXML
	private NumberAxis y;

	@FXML
	private LineChart<?, ?> lineChart;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		lineChart.setTitle("first try");

		XYChart.Series series = new XYChart.Series();
		series.setName("arhivo #US30");

		File file = new File("#US30 prices.txt");
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
			int contador = 0;
			String line = "";
			while (line != null) {
				line = bf.readLine();
				if (line != null) {

					String[] temp = line.split(",");
					String dmy = temp[1].substring(1);
					String[] temp2 = dmy.split(" ");
					String[] monthA = temp2[0].split("/");
					String priceS = temp[2].substring(1);
					double price = Double.parseDouble(priceS);

					if (contador == 500) {

						series.getData().add(new XYChart.Data(temp2[0], price));

						contador = 0;

					} else {
						contador++;
					}

				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		XYChart.Series series2 = new XYChart.Series();

		series2.setName("pa que veas que funciona xd");
		series2.getData().add(new XYChart.Data("1", 145));
		series2.getData().add(new XYChart.Data("784", 871));
		series2.getData().add(new XYChart.Data("7881", 4514));
		series2.getData().add(new XYChart.Data("8", 77));

		lineChart.getData().addAll(series, series2);

	}

}
