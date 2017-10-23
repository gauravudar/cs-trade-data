package com.cs.dto;

public enum OptionExerciseStyle {
    EUROPEAN("EUROPEAN"), AMERICAN("AMERICAN");

    private final String code;

    OptionExerciseStyle(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
