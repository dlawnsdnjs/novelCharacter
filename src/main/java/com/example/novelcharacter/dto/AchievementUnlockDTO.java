package com.example.novelcharacter.dto;

public class AchievementUnlockDTO {
    private long novelNum;
    private long episodeNum;
    private long characterNum;
    private long achievementNum;

    @Override
    public String toString() {
        return "AchievementUnlockDTO{" +
                "novelNum=" + novelNum +
                ", episodeNum=" + episodeNum +
                ", characterNum=" + characterNum +
                ", achievementNum=" + achievementNum +
                '}';
    }

    public long getNovelNum() {
        return novelNum;
    }

    public void setNovelNum(long novelNum) {
        this.novelNum = novelNum;
    }

    public long getEpisodeNum() {
        return episodeNum;
    }

    public void setEpisodeNum(long episodeNum) {
        this.episodeNum = episodeNum;
    }

    public long getCharacterNum() {
        return characterNum;
    }

    public void setCharacterNum(long characterNum) {
        this.characterNum = characterNum;
    }

    public long getAchievementNum() {
        return achievementNum;
    }

    public void setAchievementNum(long achievementNum) {
        this.achievementNum = achievementNum;
    }
}
