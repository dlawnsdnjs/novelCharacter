package com.example.novelcharacter.dto;

public class PermanentItemEffectDTO {
    private long itemNum;
    private long statNum;
    private String statType;
    private long statValue;

    @Override
    public String toString() {
        return "PermanentItemEffectDTO{" +
                "itemNum=" + itemNum +
                ", statNum=" + statNum +
                ", statType='" + statType + '\'' +
                ", statValue=" + statValue +
                '}';
    }

    public long getItemNum() {
        return itemNum;
    }

    public void setItemNum(long itemNum) {
        this.itemNum = itemNum;
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
