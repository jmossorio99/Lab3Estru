package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class LimitReader{

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
	
	public LimitReader(String limitSup,String limitInf,String fileName) {
		this.limitSup=limitSup;
		this.limitInf=limitInf;
		this.fileName=fileName;
		
		String [] tempSup=limitSup.split(" ");
		String [] dmySup=tempSup[0].split("/");
		String [] hmSup = tempSup[1].split(":");
		
		String [] tempInf=limitInf.split(" ");
		String [] dmyInf=tempInf[0].split("/");
		String [] hmInf = tempInf[1].split(":");
		
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
	
	public AVLTree getAlvOnLimit(){
		
		AVLTree avl = new AVLTree();
		
		File file = new File(fileName);
		
		String fileName = file.getName();
		  
		  if(fileName.contains(".txt")) {
			  
			  try {
				
				  
				  FileInputStream fis = new FileInputStream(file);
				  
				  BufferedReader bf = new BufferedReader( new InputStreamReader(fis));
				  boolean firstFound=false;
				  boolean stop=false;
				  
				  while(!firstFound || !stop) {
					  
					  String line = bf.readLine();
					  String [] firstSplit = line.split(",");
					  String date=firstSplit[1].substring(1);
					  
					  if(isOnTheLimit(date)) {
						  firstFound=true;
						  
						  // hacer lo que quiera hacer con el aki (esta en los limites)
						  
					  }else {
						
						  if(firstFound==true) {
							  stop=true;
						  }
						  
					  }
					  
				  }
				 bf.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		
		  
		  return avl;
	}
	
	public RBTree getRbOnLimit() {
		RBTTree rbt = new RBTree();
		
        File file = new File(fileName);
		
		String fileName = file.getName();
		  
		  if(fileName.contains(".txt")) {
			  
			  try {
				
				  
				  FileInputStream fis = new FileInputStream(file);
				  
				  BufferedReader bf = new BufferedReader( new InputStreamReader(fis));
				  boolean firstFound=false;
				  boolean stop=false;
				  
				  while(!firstFound || !stop) {
					  
					  String line = bf.readLine();
					  String [] firstSplit = line.split(",");
					  String date=firstSplit[1].substring(1);
					  
					  if(isOnTheLimit(date)) {
						  firstFound=true;
						  
						  // hacer lo que quiera hacer con el aki (esta en los limites)
						  
					  }else {
						
						  if(firstFound==true) {
							  stop=true;
						  }
						  
					  }
					  
				  }
				 bf.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		
		return rbt;
	}
	
	public boolean isOnTheLimit(String date) {
		boolean onTheLimit=false;
		
		String [] temp=date.split(" ");
		
		String [] dmy=temp[0].split("/");
		String [] hm=temp[1].split(":");
		
		int day = Integer.parseInt(dmy[0]);
		int month = Integer.parseInt(dmy[1]);
		int year = Integer.parseInt(dmy[2]);
		int hour = Integer.parseInt(hm[0]);
		int minute = Integer.parseInt(hm[1]);
		
		
		//año
		if(year>yearLimitInf && year<yearLimitSup) {
			onTheLimit=true;
		}
		else if(year>=yearLimitInf && year<=yearLimitSup) {
			
			//mes
			if(month>monthLimitInf && month<monthLimitSup) {
				onTheLimit=true;
			}
			else if(month>=monthLimitInf && month<=monthLimitSup) {
				
				//dia
				if(day>dayLimitInf && day<dayLimitSup) {
					onTheLimit=true;
				}
				else if(day>=dayLimitInf && day<=dayLimitSup) {
					
					//hora
					if(hour>hourLimitInf && hour<hourLimitSup) {
						onTheLimit=true;
					}
					else if(hour>=hourLimitInf && hour<=hourLimitSup) {
						
						//minutos
						if(minute>minuteLimitInf && minute<minuteLimitSup) {
							onTheLimit=true;
						}
						
					}
					
				}
				
			}
		}
		
		return onTheLimit;
	}
	
}
