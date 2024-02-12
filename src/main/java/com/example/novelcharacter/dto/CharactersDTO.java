package com.example.novelcharacter.dto;

public class CharactersDTO {
    private long novelNum;
    private String characterName;
    private String characterJob;

    @Override
    public String toString() {
        return "BaseStatDTO{" +
                "novelNum=" + novelNum +
                ", characterName='" + characterName + '\'' +
                ", characterJob='" + characterJob + '\'' +
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

    public String getCharacterJob() {
        return characterJob;
    }

    public void setCharacterJob(String characterJob) {
        this.characterJob = characterJob;
    }
}
