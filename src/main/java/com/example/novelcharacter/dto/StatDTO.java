package com.example.novelcharacter.dto;

public class StatDTO {
    private long statCode;
    private String statName;

    @Override
    public String toString() {
        return "StatDTO{" +
                "statCode=" + statCode +
                ", statName='" + statName + '\'' +
                '}';
    }

    public long getStatCode() {
        return statCode;
    }

    public void setStatCode(long statCode) {
        this.statCode = statCode;
    }

    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }
}
