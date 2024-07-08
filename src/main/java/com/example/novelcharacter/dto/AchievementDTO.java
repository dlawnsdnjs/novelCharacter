package com.example.novelcharacter.dto;

public class AchievementDTO {
    private long novelNum;
    private long achievementNum;
    private String achievementName;
    private String achievementDetail;

    @Override
    public String toString() {
        return "AchievementDTO{" +
                "novelNum=" + novelNum +
                ", achievementNum=" + achievementNum +
                ", achievementName='" + achievementName + '\'' +
                ", achievementDetail='" + achievementDetail + '\'' +
                '}';
    }

    public long getNovelNum() {
        return novelNum;
    }

    public void setNovelNum(long novelNum) {
        this.novelNum = novelNum;
    }

    public long getAchievementNum() {
        return achievementNum;
    }

    public void setAchievementNum(long achievementNum) {
        this.achievementNum = achievementNum;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public String getAchievementDetail() {
        return achievementDetail;
    }

    public void setAchievementDetail(String achievementDetail) {
        this.achievementDetail = achievementDetail;
    }
}
