package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class HighestGrowthFinder {

	public final static int DAY = 0;
	public final static int MONTH = 1;
	public final static int YEAR = 2;

	private String fileName = "";

	public HighestGrowthFinder(String fileName) {
		this.fileName = fileName;
	}

	public String findHighestGrowthMonth() throws IOException {

		boolean finished = false;
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		BufferedReader in = new BufferedReader(new InputStreamReader(fis));
		String currentMonth = "";
		double currentPrice = 0;
		double highest = Double.MIN_VALUE;
		String highestMonth = "";

		String[] parts = in.readLine().split(",");
		String[] dateNTime = parts[1].substring(1).split(" ");
		String[] date = dateNTime[0].split("/");
		String month = date[1];
		currentMonth = month;
		double startPrice = Double.parseDouble(parts[2]);
		double finalPrice = 0;
		currentPrice = startPrice;

		while (!finished) {

			String line = in.readLine();
			if (line != null) {
				parts = line.split(",");
				dateNTime = parts[1].substring(1).split(" ");
				date = dateNTime[0].split("/");
				month = date[1];
				if (month != currentMonth) {
					finalPrice = currentPrice;
					if (finalPrice - startPrice > highest) {
						highest = finalPrice - startPrice;
						highestMonth = currentMonth;
						currentMonth = month;
					}
					currentPrice = Double.parseDouble(parts[2]);
					startPrice = currentPrice;
				} else {
					currentPrice = Double.parseDouble(parts[2]);
				}
			} else {
				finished = true;
			}

		}
		return highestMonth;
	}

}
