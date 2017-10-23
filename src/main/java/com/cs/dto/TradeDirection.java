package com.cs.dto;

public enum TradeDirection {
    BUY("BUY"),SELL("SELL");

    private final String code;

    TradeDirection(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
