package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HighestGrowthFinder {

	public final static int DAY = 0;
	public final static int MONTH = 1;
	public final static int YEAR = 2;

	private String fileName = "";
	private String initialDate;
	private String finalDate;
	private ArrayList<String> fileNames;

	public HighestGrowthFinder(String fileName) {
		this.fileName = fileName;
	}

	public HighestGrowthFinder(String initialDate, String finalDate, ArrayList<String> fileNames) {

		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.fileNames = fileNames;

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
		String[] dateNTime = (parts[1].substring(1)).split(" ");
		String[] date = dateNTime[0].split("/");
		String month = date[1];
		currentMonth = month;
		double startPrice = Double.parseDouble(parts[2].substring(1));
		double finalPrice = 0;
		currentPrice = startPrice;

		while (!finished) {

			String line = in.readLine();
			if (line != null) {
				parts = line.split(",");
				dateNTime = (parts[1].substring(1)).split(" ");
				date = dateNTime[0].split("/");
				month = date[1];
				if (month != currentMonth) {
					if ((finalPrice - startPrice) > highest) {
						highest = finalPrice - startPrice;
						highestMonth = currentMonth;
						currentMonth = month;
					}
					finalPrice = currentPrice;
					currentPrice = Double.parseDouble(parts[2].substring(1));
					startPrice = currentPrice;
				} else {
					currentPrice = Double.parseDouble(parts[2].substring(1));
				}
			} else {
				finished = true;
			}

		}
		in.close();
		return highestMonth;
	}

	public String findHighestGrowthDay() throws IOException {

		boolean finished = false;
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		BufferedReader in = new BufferedReader(new InputStreamReader(fis));
		String currentDay = "";
		double currentPrice = 0;
		double highest = Double.MIN_VALUE;
		String highestDay = "";
		String[] parts = in.readLine().split(",");
		String[] dateNTime = (parts[1].substring(1)).split(" ");
		String[] date = dateNTime[0].split("/");
		String day = date[0];
		currentDay = day;
		double startPrice = Double.parseDouble(parts[2].substring(1));
		double finalPrice = 0;
		currentPrice = startPrice;

		while (!finished) {

			String line = in.readLine();
			if (line != null) {
				parts = line.split(",");
				dateNTime = (parts[1].substring(1)).split(" ");
				date = dateNTime[0].split("/");
				day = date[0];
				if (day != currentDay) {
					if ((finalPrice - startPrice) > highest) {
						highest = finalPrice - startPrice;
						highestDay = currentDay;
						currentDay = day;
					}
					finalPrice = currentPrice;
					currentPrice = Double.parseDouble(parts[2].substring(1));
					startPrice = currentPrice;
				} else {
					currentPrice = Double.parseDouble(parts[2].substring(1));
				}
			} else {
				finished = true;
			}

		}
		in.close();
		return highestDay;

	}

	public String findHighestGrowthYear() throws IOException {

		boolean finished = false;
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		BufferedReader in = new BufferedReader(new InputStreamReader(fis));
		String currentYear = "";
		double currentPrice = 0;
		double highest = Double.MIN_VALUE;
		String highestYear = "";
		String[] parts = in.readLine().split(",");
		String[] dateNTime = (parts[1].substring(1)).split(" ");
		String[] date = dateNTime[0].split("/");
		String year = date[2];
		currentYear = year;
		double startPrice = Double.parseDouble(parts[2].substring(1));
		double finalPrice = 0;
		currentPrice = startPrice;

		while (!finished) {

			String line = in.readLine();
			if (line != null) {
				parts = line.split(",");
				dateNTime = (parts[1].substring(1)).split(" ");
				date = dateNTime[0].split("/");
				year = date[2];
				if (year != currentYear) {
					if ((finalPrice - startPrice) > highest) {
						highest = finalPrice - startPrice;
						highestYear = currentYear;
						currentYear = year;
					}
					finalPrice = currentPrice;
					currentPrice = Double.parseDouble(parts[2].substring(1));
					startPrice = currentPrice;
				} else {
					currentPrice = Double.parseDouble(parts[2].substring(1));
				}
			} else {
				finished = true;
			}

		}
		in.close();
		return highestYear;

	}

	public String findThreeGreatestGrowth() throws IOException {

		String result = "";
		String[] names = new String[3];
		names[0] = "";
		names[1] = "";
		names[2] = "";
		Double[] values = new Double[3];
		values[0] = 0.0;
		values[1] = 0.0;
		values[2] = 0.0;
		for (int i = 0; i < fileNames.size(); i++) {

			boolean firstFound = false;
			boolean stop = false;
			boolean next = true;
			File file = new File(fileNames.get(i));
			if (file.exists()) {

				String name = "";
				double priceI = 0.0;
				double cPrice = 0.0;
				double priceF = 0.0;
				double growth = 0.0;
				FileInputStream fis = new FileInputStream(file);
				BufferedReader in = new BufferedReader(new InputStreamReader(fis));
				LimitReader lr = new LimitReader(initialDate, finalDate, fileNames.get(i));
				String line = in.readLine();
				while (line != null && !stop || !firstFound) {

					String[] firstSplit = line.split(",");
					String date = firstSplit[1].substring(1);
					if (lr.isOnTheLimit(date)) {
						if (!firstFound) {
							firstFound = true;
							priceI = Double.parseDouble(firstSplit[2].substring(1));
						}
						cPrice = Double.parseDouble(firstSplit[2].substring(1));
					} else {
						if (firstFound) {
							priceF = cPrice;
							stop = true;
						}
					}
					line = in.readLine();

				}
				growth = priceF - priceI;
				for (int j = 0; j < values.length && next; j++) {
					System.out.println(Math.abs(growth) + " " + Math.abs(values[j]));
					if (Math.abs(growth) > Math.abs(values[j])) {
						name = fileNames.get(i).split(" ")[0];
						values[j] = growth;
						names[j] = name;
						next = false;
					}

				}

				in.close();
			}

		}

		for (int i = 0; i < names.length; i++) {
			result += names[i] + " ";
		}
		return result;

	}

}
