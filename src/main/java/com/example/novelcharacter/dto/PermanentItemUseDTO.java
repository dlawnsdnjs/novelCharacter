package com.example.novelcharacter.dto;

public class PermanentItemUseDTO {
    private long novelNum;
    private long episodeNum;
    private long characterNum;
    private long itemNum;

    @Override
    public String toString() {
        return "PermanentItemUseDTO{" +
                "novelNum=" + novelNum +
                ", episodeNum=" + episodeNum +
                ", characterNum=" + characterNum +
                ", itemNum=" + itemNum +
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

    public long getItemNum() {
        return itemNum;
    }

    public void setItemNum(long itemNum) {
        this.itemNum = itemNum;
    }
}
