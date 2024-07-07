package com.example.novelcharacter.dto;

public class EquipmentDTO {
    private long novelNum;
    private long equipmentNum;
    private String equipmentName;
    private String equipmentType;

    @Override
    public String toString() {
        return "EquipmentDTO{" +
                "novelNum=" + novelNum +
                ", equipmentNum=" + equipmentNum +
                ", equipmentName='" + equipmentName + '\'' +
                ", equipmentType='" + equipmentType + '\'' +
                '}';
    }

    public long getNovelNum() {
        return novelNum;
    }

    public void setNovelNum(long novelNum) {
        this.novelNum = novelNum;
    }

    public long getEquipmentNum() {
        return equipmentNum;
    }

    public void setEquipmentNum(long equipmentNum) {
        this.equipmentNum = equipmentNum;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }
}
