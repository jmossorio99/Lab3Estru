package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		BufferedReader in = new BufferedReader(new InputStreamReader(fis));
		double highest = Double.MIN_VALUE;
		double startMonth = 0;
		double endMonth = 0;
		while (in.readLine() != null) {

			
			
		}

	}

}
