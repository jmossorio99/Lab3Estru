package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;

public class LimitReader {

	private String limitSup;
	private String limitInf;
	private int yearLimitSup;
	private int yearLimitInf;
	private int monthLimitSup;
	private int monthLimitInf;
	private int dayLimitSup;
	private int dayLimitInf;
	private int hourLimitSup;
	private int hourLimitInf;
	private int minuteLimitSup;
	private int minuteLimitInf;
	private String fileName;

	public LimitReader(String limitSup, String limitInf, String fileName) {
		this.limitSup = limitSup;
		this.limitInf = limitInf;
		this.fileName = fileName;

		String[] tempSup = limitSup.split(" ");
		String[] dmySup = tempSup[0].split("/");
		String[] hmSup = tempSup[1].split(":");

		String[] tempInf = limitInf.split(" ");
		String[] dmyInf = tempInf[0].split("/");
		String[] hmInf = tempInf[1].split(":");

		yearLimitSup = Integer.parseInt(dmySup[2]);
		yearLimitInf = Integer.parseInt(dmyInf[2]);
		monthLimitSup = Integer.parseInt(dmySup[1]);
		monthLimitInf = Integer.parseInt(dmyInf[1]);
		dayLimitSup = Integer.parseInt(dmySup[0]);
		dayLimitInf = Integer.parseInt(dmyInf[0]);
		hourLimitSup = Integer.parseInt(hmSup[0]);
		hourLimitInf = Integer.parseInt(hmInf[0]);
		minuteLimitSup = Integer.parseInt(hmSup[1]);
		minuteLimitInf = Integer.parseInt(hmInf[1]);
	}

	public AVLTree<String> getAlvOnLimit() throws IOException {

		AVLTree<String> avl = new AVLTree<String>();
		File file = new File(fileName);

		FileInputStream fis = new FileInputStream(file);
		BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
		boolean firstFound = false;
		boolean stop = false;

		while (!firstFound || !stop) {

			String line = bf.readLine();
			if (line != null) {
				String[] firstSplit = line.split(",");
				String date = firstSplit[1].substring(1);

				if (isOnTheLimit(date)) {
					firstFound = true;
					String price = firstSplit[2].substring(1);
					avl.insert(price, date, fileName);

				} else {

					if (firstFound == true) {
						stop = true;
					}

				}
			} else {
				break;
			}

		}
		bf.close();

		return avl;
	}

	public RBTree<String> getRbOnLimit() throws IOException {
		RBTree<String> rbt = new RBTree<String>();

		File file = new File(fileName);

		String fileName = file.getName();

		if (fileName.contains(".txt")) {

			FileInputStream fis = new FileInputStream(file);

			BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
			boolean firstFound = false;
			boolean stop = false;

			while (!firstFound || !stop) {

				String line = bf.readLine();
				if (line != null) {
					String[] firstSplit = line.split(",");
					String date = firstSplit[1].substring(1);

					if (isOnTheLimit(date)) {
						firstFound = true;
						String price = firstSplit[2].substring(1);
						RBNode<String> node = new RBNode<String>(price, date, fileName);
						rbt.insert(node);
					} else {

						if (firstFound == true) {
							stop = true;
						}
					}

				} else {
					break;
				}

			}
			bf.close();

		}

		return rbt;
	}

	@SuppressWarnings("deprecation")
	public boolean isOnTheLimit(String date) {
		boolean onTheLimit = false;

		String[] temp = date.split(" ");

		String[] dmy = temp[0].split("/");
		String[] hm = temp[1].split(":");

		int day = Integer.parseInt(dmy[0]);
		int month = Integer.parseInt(dmy[1]);
		int year = Integer.parseInt(dmy[2]);
		int hour = Integer.parseInt(hm[0]);
		int minute = Integer.parseInt(hm[1]);

		Date start = new Date(yearLimitSup, monthLimitSup, dayLimitSup, hourLimitSup, minuteLimitSup);
		Date current = new Date(year, month, day, hour, minute);
		Date end = new Date(yearLimitInf, monthLimitInf, dayLimitInf, hourLimitInf, minuteLimitInf);

		if (start.compareTo(current) == 0 || end.compareTo(current) == 0
				|| (start.after(current) && end.before(current))) {
			onTheLimit = true;
		}

		return onTheLimit;
	}

}
