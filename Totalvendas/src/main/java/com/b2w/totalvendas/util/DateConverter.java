package com.b2w.totalvendas.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
	
	 public static Date read(String dateString){
		 DateFormat format = new SimpleDateFormat("dd-MM-yy");
		 Date data = null;
		 try {
			data = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return data;
		
	}

}
