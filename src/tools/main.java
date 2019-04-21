package tools;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class main {

	private static Random ramdom;
	private static int data=12500;
	
	public static void main(String[] args) {
		 
		ramdom = new Random();
		double original = 25600.0;
		String us="#US30";	
		int day =3;
		int month=3;
		int year=2019;
        int hour=7;
        int minutes=52;
        int datos=0;
        
		try {
			File output = new File("output.txt");
			PrintWriter pr = new PrintWriter(output);
			
			while(datos<data) {
		
				
				while(month>=1 && datos<data) {
					
					
					while(day>=1 && datos<data) {
						
						
						while(hour>=0 && datos<data) {
							
							
							while(minutes>=0 && datos<data) {
							
								
								double decimales=ramdom.nextInt(10)/10.0;
								double number = original + (100+ramdom.nextInt(150))+decimales;
								String toWrite=us+", "+day+"/"+month+"/"+year+" "+hour+":"+minutes+", "+number;
								pr.println(toWrite);
								datos++;
								System.out.println(""+datos);
								
								minutes--;
							}
							minutes=59;
							hour--;
						}
						hour=23;
						day--;
					}
					day=30;
					month--;
				}
				month=12;
				year--;
			}

			pr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/**
		 * 
		 * double decimales=ramdom.nextInt(10)/10.0;
					double number = original + (100+ramdom.nextInt(150))+decimales;
					String toWrite=us+", "+day+"/2/2019 "+hour+":"+minutes+", "+number;
					pr.println(toWrite);
					datos++;
					System.out.println(""+datos);
					minutes++;
		 */
	}

	
}
