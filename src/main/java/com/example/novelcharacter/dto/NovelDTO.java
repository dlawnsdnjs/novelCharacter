package com.example.novelcharacter.dto;

public class NovelDTO {
    private long novelNum;
    private String novelTitle;
    private String writer;

    @Override
    public String toString() {
        return "novelDTO{" +
                "novelNum=" + novelNum +
                ", novelTitle='" + novelTitle + '\'' +
                ", titleAlias='" + writer + '\'' +
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

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
