package com.wifihi.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class myDate {
 
	static public int outputCurrentDateandTime(){
		GregorianCalendar currenttime = new GregorianCalendar();
		int time = currenttime.get(Calendar.HOUR_OF_DAY) * 3600
				+ currenttime.get(Calendar.MINUTE) * 60
				+ currenttime.get(Calendar.SECOND);
		System.out.print(currenttime.get(Calendar.YEAR) + "-"
				+ currenttime.get(Calendar.MONTH) + "-"
				+ currenttime.get(Calendar.DATE) + "-"
				+ currenttime.get(Calendar.HOUR_OF_DAY) + "-"
				+ currenttime.get(Calendar.MINUTE) + "-"
				+ currenttime.get(Calendar.SECOND) + "-"
				+ currenttime.get(Calendar.MILLISECOND));	
		return time;
	}
	static public int outputCurrentTime(){
		GregorianCalendar currenttime = new GregorianCalendar();
		int time = currenttime.get(Calendar.HOUR_OF_DAY) * 3600
				+ currenttime.get(Calendar.MINUTE) * 60
				+ currenttime.get(Calendar.SECOND);
		System.out.print(currenttime.get(Calendar.YEAR) + "-"
				+ currenttime.get(Calendar.MONTH) + "-"
				+ currenttime.get(Calendar.DATE) + "-"
				+ currenttime.get(Calendar.HOUR_OF_DAY) + "-"
				+ currenttime.get(Calendar.MINUTE) + "-"
				+ currenttime.get(Calendar.SECOND) + "-"
				+ currenttime.get(Calendar.MILLISECOND));	
		return time;
	}
	
	static public int CurrentTime(){
		GregorianCalendar currenttime = new GregorianCalendar();
		int time = currenttime.get(Calendar.HOUR_OF_DAY) * 3600
				+ currenttime.get(Calendar.MINUTE) * 60
				+ currenttime.get(Calendar.SECOND);	
		return time;
	}
	
	static public void  main(String[] str){		
		new myDate().outputCurrentTime();
	}
}
