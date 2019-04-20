package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class GraphWindowController implements Initializable {

	@FXML
	private NumberAxis x;

	@FXML
	private NumberAxis y;
	
	 @FXML
    private ListView<?> listView;

	 @FXML
	 private Button addButton;

	 @FXML
	 private Button removeButton;

	    @FXML
	    void addButtonClicked(ActionEvent event) {

	    }

	    @FXML
	    void removeButtonClicked(ActionEvent event) {

	    }

	@FXML
	private LineChart<Number, Number> lineChart;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
        lineChart.setTitle("Grafh");
		

	}
	
	public void grafhOneFile(String fileName) {
		
		XYChart.Series series = addSeries(fileName);
		lineChart.getData().addAll(series);
		
	}
	
	public void grafhTwoFiles(String fileName1,String fileName2) {
		
		XYChart.Series series = addSeries(fileName1);
		XYChart.Series series2 = addSeries(fileName2);
		lineChart.getData().addAll(series,series2);
		
	}
	
	public void grafhThreeFiles(String fileName1,String fileName2,String fileName3) {
		
		XYChart.Series series = addSeries(fileName1);
		XYChart.Series series2 = addSeries(fileName2);
		XYChart.Series series3 = addSeries(fileName3);
		lineChart.getData().addAll(series,series2,series3);
		
	}
	
	public XYChart.Series addSeries(String fileName) {
		
		XYChart.Series series = new XYChart.Series();
		series.setName(fileName);

		File file = new File(fileName);
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

					
					
					if(contador==500) {
					series.getData().add(new XYChart.Data(temp2[0], price));

					contador=0;
					}else {
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
		
		return series;
	}

}
