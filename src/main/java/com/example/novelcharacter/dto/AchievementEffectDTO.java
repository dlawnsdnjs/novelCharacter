package com.example.novelcharacter.dto;

public class AchievementEffectDTO {
    private long achievementNum;
    private long statNum;
    private String statType;
    private long statValue;

    @Override
    public String toString() {
        return "AchievementEffectDTO{" +
                "achievementNum=" + achievementNum +
                ", statNum=" + statNum +
                ", statType='" + statType + '\'' +
                ", statValue=" + statValue +
                '}';
    }

    public long getAchievementNum() {
        return achievementNum;
    }

    public void setAchievementNum(long achievementNum) {
        this.achievementNum = achievementNum;
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
