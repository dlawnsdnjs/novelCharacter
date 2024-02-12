package com.example.novelcharacter.dto;

import java.util.List;

public class CharacterAllStatDTO {
    private long novelNum;
    private String characterName;
    private String characterJob;
    private List<CharacterStatDTO> characterStatDTOS;

    @Override
    public String toString() {
        return "CharacterAllStat{" +
                "novelNum=" + novelNum +
                ", characterName='" + characterName + '\'' +
                ", characterJob='" + characterJob + '\'' +
                ", characterStatDTOS=" + characterStatDTOS +
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

    public List<CharacterStatDTO> getCharacterStatDTOS() {
        return characterStatDTOS;
    }

    public void setCharacterStatDTOS(List<CharacterStatDTO> characterStatDTOS) {
        this.characterStatDTOS = characterStatDTOS;
    }
}
