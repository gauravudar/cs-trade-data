package com.cs.dto;

public enum OptionStrategy {
    CALL("CALL"), PUT("PUT");

    private final String code;

    OptionStrategy(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

 }
