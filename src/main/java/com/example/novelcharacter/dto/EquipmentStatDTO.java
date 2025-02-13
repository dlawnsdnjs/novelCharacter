package com.example.novelcharacter.dto;

public class EquipmentStatDTO {
    private long equipmentNum;
    private long statCode;
    private String statType;
    private long value;

    @Override
    public String toString() {
        return "EquipmentEffectDTO{" +
                "equipmentNum=" + equipmentNum +
                ", statNum=" + statCode +
                ", statType='" + statType + '\'' +
                ", statValue=" + value +
                '}';
    }

    public long getEquipmentNum() {
        return equipmentNum;
    }

    public void setEquipmentNum(long equipmentNum) {
        this.equipmentNum = equipmentNum;
    }

    public long getStatCode() {
        return statCode;
    }

    public void setStatCode(long statCode) {
        this.statCode = statCode;
    }

    public String getStatType() {
        return statType;
    }

    public void setStatType(String statType) {
        this.statType = statType;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
