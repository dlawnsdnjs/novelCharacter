package com.example.novelcharacter.dto;

public class CharacterDTO {
    private long novelNum;
    private String characterName;
    private long characterNum;

    @Override
    public String toString() {
        return "BaseStatDTO{" +
                "novelNum=" + novelNum +
                ", characterName='" + characterName + '\'' +
                ", characterJob='" + characterNum + '\'' +
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

    public long getCharacterNum() {
        return characterNum;
    }

    public void setCharacterNum(long characterNum) {
        this.characterNum = characterNum;
    }
}
