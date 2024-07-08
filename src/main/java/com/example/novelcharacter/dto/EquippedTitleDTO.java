package com.example.novelcharacter.dto;

public class EquippedTitleDTO {
    private long novelNum;
    private long episodeNum;
    private long characterNum;
    private long titleNum;

    @Override
    public String toString() {
        return "EquippedTitleDTO{" +
                "novelNum=" + novelNum +
                ", episodeNum=" + episodeNum +
                ", characterNum=" + characterNum +
                ", titleNum=" + titleNum +
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

    public long getTitleNum() {
        return titleNum;
    }

    public void setTitleNum(long titleNum) {
        this.titleNum = titleNum;
    }
}
