package com.cs.viewobject;

public class DataValidationError {
	private String tradeIdentifier;
	private String message;
	
	public DataValidationError(String tradeIdentifier, String message){
		this.tradeIdentifier=tradeIdentifier;
		this.message=message;
	}
	
	public String getTradeIdentifier() {
		return tradeIdentifier;
	}
	public void setTradeIdentifier(String tradeIdentifier) {
		this.tradeIdentifier = tradeIdentifier;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
