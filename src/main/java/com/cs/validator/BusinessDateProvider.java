
package com.cs.validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.json.JSONException;
import org.json.JSONObject;

public class BusinessDateProvider {
    
	public static boolean isBusinessDateFallsOnWeekendOrNonWorkingDay(LocalDate valueDate) {
        return isWeekEnd(valueDate)&& isWorkingDayForCurrency(valueDate);
    }

    private static boolean isWeekEnd(LocalDate date) {
        int dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DateTimeConstants.SATURDAY || dayOfWeek == DateTimeConstants.SUNDAY;
    }
    
    private static boolean isWorkingDayForCurrency(LocalDate date){
    	try {
    		URL url = new URL("http://api.fixer.io/"+date.toString("yyyy-MM-dd"));
    		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    		conn.setRequestMethod("GET");
    		conn.setRequestProperty("Accept", "application/json");

    		if (conn.getResponseCode() != 200) {
    			throw new RuntimeException("Failed : HTTP error code : "
    					+ conn.getResponseCode());
    		}

    		BufferedReader br = new BufferedReader(new InputStreamReader(
    			(conn.getInputStream())));

    		String output;
    		StringBuilder sb= new StringBuilder();
    		while ((output = br.readLine()) != null) {
    			sb.append(output);
    		}
    		try {
    			final JSONObject obj = new JSONObject(sb.toString());
    			String valDate = obj.getString("date");
    			if(valDate.equals(date)){
    				return false;    			
    		}
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
    		conn.disconnect();

    	  } catch (MalformedURLException e) {
    		e.printStackTrace();
    	  } catch (IOException e) {
    		e.printStackTrace();
    	  }
    	return true;
    }
}
