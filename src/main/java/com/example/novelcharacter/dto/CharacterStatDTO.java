package com.example.novelcharacter.dto;

public class CharacterStatDTO {
    private long novelNum;
    private String characterName;
    private long statCode;
    private long value;

    @Override
    public String toString() {
        return "CharacterStatDTO{" +
                "novelNum=" + novelNum +
                ", characterName='" + characterName + '\'' +
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

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
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
