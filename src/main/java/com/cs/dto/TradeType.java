package com.cs.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TradeType {
	Spot("Spot"),
	Forward("Forward"),
	VanillaOption("VanillaOption");
	
	private String value;

	TradeType(String value) {
        this.value = value;
    }

    public String getTradeType() {
        return value;
    }
    
    @JsonValue
    final String value() {
        return this.value;
    }
}
