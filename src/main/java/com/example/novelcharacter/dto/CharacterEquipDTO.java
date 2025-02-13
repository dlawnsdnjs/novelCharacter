package com.example.novelcharacter.dto;

public class CharacterEquipDTO {
    private long episodeNum;
    private long characterNum;
    private long equipmentNum;

    @Override
    public String toString() {
        return "CharacterEquipDTO{" +
                ", episodeNum=" + episodeNum +
                ", characterNum=" + characterNum +
                ", equipmentNum=" + equipmentNum +
                '}';
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

    public long getEquipmentNum() {
        return equipmentNum;
    }

    public void setEquipmentNum(long equipmentNum) {
        this.equipmentNum = equipmentNum;
    }
}
