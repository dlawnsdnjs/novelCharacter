package com.example.novelcharacter.dto;

public class TitleEffectDTO {
    private long titleNum;
    private long statNum;
    private String statType;
    private long statValue;

    @Override
    public String toString() {
        return "TitleEffectDTO{" +
                "titleNum=" + titleNum +
                ", statNum=" + statNum +
                ", statType='" + statType + '\'' +
                ", statValue=" + statValue +
                '}';
    }

    public long getTitleNum() {
        return titleNum;
    }

    public void setTitleNum(long titleNum) {
        this.titleNum = titleNum;
    }

    public long getStatNum() {
        return statNum;
    }

    public void setStatNum(long statNum) {
        this.statNum = statNum;
    }

    public String getStatType() {
        return statType;
    }

    public void setStatType(String statType) {
        this.statType = statType;
    }

    public long getStatValue() {
        return statValue;
    }

    public void setStatValue(long statValue) {
        this.statValue = statValue;
    }
}
