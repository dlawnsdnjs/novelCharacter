package com.example.novelcharacter.dto;

public class EquipmentEffectDTO {
    private long equipmentNum;
    private long statNum;
    private String statType;
    private long statValue;

    @Override
    public String toString() {
        return "EquipmentEffectDTO{" +
                "equipmentNum=" + equipmentNum +
                ", statNum=" + statNum +
                ", statType='" + statType + '\'' +
                ", statValue=" + statValue +
                '}';
    }

    public long getEquipmentNum() {
        return equipmentNum;
    }

    public void setEquipmentNum(long equipmentNum) {
        this.equipmentNum = equipmentNum;
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
