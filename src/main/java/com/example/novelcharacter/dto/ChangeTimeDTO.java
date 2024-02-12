package com.example.novelcharacter.dto;

public class ChangeTimeDTO {
    private long novelNum;
    private String characterName;
    private long time;
    private String detail;

    @Override
    public String toString() {
        return "ChangeTime{" +
                "novelNum=" + novelNum +
                ", characterName='" + characterName + '\'' +
                ", time=" + time +
                ", detail='" + detail + '\'' +
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
