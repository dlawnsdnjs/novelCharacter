package com.example.novelcharacter.dto;

public class EpisodeCharacter {
    private long novelNum;
    private long episodeNum;
    private long characterNum;

    @Override
    public String toString() {
        return "EpisodeCharacter{" +
                "novelNum=" + novelNum +
                ", episodeNum=" + episodeNum +
                ", characterNum=" + characterNum +
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
}
