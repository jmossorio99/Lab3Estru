package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;

public class GraphWindowController implements Initializable {

	@FXML
	private CategoryAxis x;
	@FXML
	private NumberAxis y;
	@FXML
	private ListView<String> listView;
	@FXML
	private ImageView addImg;
	@FXML
	private ImageView deleteImg;

	private int itemsSelected;
    
	 private ArrayList<String> itemsToGraph;
	
	@FXML
	void addButtonClicked(ActionEvent event) {

		if(itemsSelected<=3) {
    		
    		String selected = listView.getSelectionModel().getSelectedItem();
    		
    		System.out.println(selected);
    		
    		if(itemsToGraph.contains(selected)) {
    			JOptionPane.showMessageDialog(null, "cannot add the same file twice");
    		}
    		else{
    			
    			itemsToGraph.add(selected);
    			
    			graphItemsToGraph();
    			
    			itemsSelected++;
    		}
    		
    		
    	}else {
    		JOptionPane.showMessageDialog(null, "uper limit 3");
    	}
    	
		
	}

	@FXML
	void removeButtonClicked(ActionEvent event) {

		String selected = listView.getSelectionModel().getSelectedItem();
    	
    	if(itemsToGraph.contains(selected)) {
    		
    		itemsToGraph.remove(selected);
    		
    		graphItemsToGraph();
    		
    		itemsSelected--;
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "cannot remove a file that isnt in the graph");
    	}
		
	}

	@FXML
	private LineChart<Number, Number> lineChart;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		itemsToGraph=new ArrayList<String>();
		itemsSelected=0;
		listView.getItems().addAll("#US30","#USSPX500","#WTI","BTCUSD","EURUSD","GBPCAD","USDJPY","XAUUSD");
		listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		y.setAutoRanging(false);
		y.setLowerBound(-3);
		y.setUpperBound(3);
		y.setTickUnit(0.05);
		lineChart.setTitle("Graph");

	}
	
public void graphItemsToGraph() {
		
		lineChart.getData().clear();
		
		if(itemsToGraph.size()==1) {
			
			graphOneFile(itemsToGraph.get(0)+" prices.txt");
			
		}
		else if(itemsToGraph.size()==2) {
			
			graphTwoFiles(itemsToGraph.get(0)+" prices.txt",itemsToGraph.get(1)+" prices.txt");
			
		}
		else if(itemsToGraph.size()==3){
			
			graphThreeFiles(itemsToGraph.get(0)+" prices.txt",itemsToGraph.get(1)+" prices.txt",itemsToGraph.get(2)+" prices.txt");	
		}
	}

	public void graphOneFile(String fileName) {

		XYChart.Series series = addSeries(fileName);
		lineChart.getData().addAll(series);

	}

	public void graphTwoFiles(String fileName1, String fileName2) {

		XYChart.Series series = addSeries(fileName1);
		XYChart.Series series2 = addSeries(fileName2);
		lineChart.getData().addAll(series, series2);

	}

	public void graphThreeFiles(String fileName1, String fileName2, String fileName3) {

		XYChart.Series series = addSeries(fileName1);
		XYChart.Series series2 = addSeries(fileName2);
		XYChart.Series series3 = addSeries(fileName3);
		lineChart.getData().addAll(series, series2, series3);

	}

	public XYChart.Series addSeries(String fileName) {

		XYChart.Series series = new XYChart.Series();
		series.setName(fileName.split(" ")[0]);

		File file = new File(fileName);
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
			int contador = 0;
			String line = bf.readLine();
			String[] temp = line.split(",");
			String dmy = temp[1].substring(1);
			String[] temp2 = dmy.split(" ");
			String[] monthA = temp2[0].split("/");
			String priceS = temp[2].substring(1);
			double priceI = Double.parseDouble(priceS);

			while (line != null) {
				if (line != null) {

					temp = line.split(",");
					dmy = temp[1].substring(1);
					temp2 = dmy.split(" ");
					monthA = temp2[0].split("/");
					priceS = temp[2].substring(1);
					double price = (Double.parseDouble(priceS) - priceI) * 100 / priceI;

					if (contador == 5000) {
						series.getData().add(new XYChart.Data(temp2[0], price));

						contador = 0;
					} else {
						contador++;
					}

				}
				line = bf.readLine();
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
