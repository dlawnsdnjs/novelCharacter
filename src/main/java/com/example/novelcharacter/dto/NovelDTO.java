package com.example.novelcharacter.dto;

public class NovelDTO {
    private long novelNum;
    private String novelTitle;
    private long uuid;

    @Override
    public String toString() {
        return "NovelDTO{" +
                "novelNum=" + novelNum +
                ", novelTitle='" + novelTitle + '\'' +
                ", uuid=" + uuid +
                '}';
    }

    public long getNovelNum() {
        return novelNum;
    }

    public void setNovelNum(long novelNum) {
        this.novelNum = novelNum;
    }

    public String getNovelTitle() {
        return novelTitle;
    }

    public void setNovelTitle(String novelTitle) {
        this.novelTitle = novelTitle;
    }

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }
}
