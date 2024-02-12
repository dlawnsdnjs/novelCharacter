package com.example.novelcharacter.dto;

public class NovelDTO {
    private long novelNum;
    private String novelTitle;
    private String titleAlias;

    @Override
    public String toString() {
        return "novelDTO{" +
                "novelNum=" + novelNum +
                ", novelTitle='" + novelTitle + '\'' +
                ", titleAlias='" + titleAlias + '\'' +
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

    public String getTitleAlias() {
        return titleAlias;
    }

    public void setTitleAlias(String titleAlias) {
        this.titleAlias = titleAlias;
    }
}
