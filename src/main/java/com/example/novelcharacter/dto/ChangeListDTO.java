package com.example.novelcharacter.dto;

public class ChangeListDTO {
    private long novelNum;
    private String characterName;
    private long time;
    private long changeCode;

    @Override
    public String toString() {
        return "ChangeListDTO{" +
                "novelNum=" + novelNum +
                ", characterName='" + characterName + '\'' +
                ", time=" + time +
                ", changeCode=" + changeCode +
                '}';
    }

    public long getNovelNum() {
        return novelNum;
    }

    public void setNovelNum(long novelNum) {
        this.novelNum = novelNum;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getChangeCode() {
        return changeCode;
    }

    public void setChangeCode(long changeCode) {
        this.changeCode = changeCode;
    }
}
