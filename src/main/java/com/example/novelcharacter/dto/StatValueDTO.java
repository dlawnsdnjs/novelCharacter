package com.example.novelcharacter.dto;

public class StatValueDTO {
    private long novelNum;



    private long episodeNUm;
    private long characterNum;
    private long statCode;
    private long value;

    @Override
    public String toString() {
        return "CharacterStatDTO{" +
                "novelNum=" + novelNum +
                ", episodeNUm=" + episodeNUm +
                ", characterNum='" + characterNum + '\'' +
                ", statCode=" + statCode +
                ", value=" + value +
                '}';
    }

    public long getNovelNum() {
        return novelNum;
    }

    public void setNovelNum(long novelNum) {
        this.novelNum = novelNum;
    }

    public long getEpisodeNUm() {
        return episodeNUm;
    }

    public void setEpisodeNUm(long episodeNUm) {
        this.episodeNUm = episodeNUm;
    }
    public long getCharacterNum() {
        return characterNum;
    }

    public void setCharacterNum(long characterNum) {
        this.characterNum = characterNum;
    }

    public long getStatCode() {
        return statCode;
    }

    public void setStatCode(long statCode) {
        this.statCode = statCode;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
