package com.example.novelcharacter.dto;

import java.util.List;

public class CharacterAllStatDTO {
    private long novelNum;
    private String characterName;
    private String characterJob;
    private List<StatValueDTO> statValueDTOS;

    @Override
    public String toString() {
        return "CharacterAllStat{" +
                "novelNum=" + novelNum +
                ", characterName='" + characterName + '\'' +
                ", characterJob='" + characterJob + '\'' +
                ", characterStatDTOS=" + statValueDTOS +
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

    public List<StatValueDTO> getCharacterStatDTOS() {
        return statValueDTOS;
    }

    public void setCharacterStatDTOS(List<StatValueDTO> statValueDTOS) {
        this.statValueDTOS = statValueDTOS;
    }
}
