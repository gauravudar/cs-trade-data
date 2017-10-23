package com.cs.utils;

import org.joda.time.LocalDate;

public class DateUtils {	
	public static LocalDate getStringDateAsLocalDate(String date){
		return new LocalDate(date);
	}
}
