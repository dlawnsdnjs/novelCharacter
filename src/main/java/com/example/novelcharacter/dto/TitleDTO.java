package com.example.novelcharacter.dto;

public class TitleDTO {
    private long novelNum;
    private long titleNum;
    private String titleName;
    private String titleDetail;

    @Override
    public String toString() {
        return "TitleDTO{" +
                "novelNum=" + novelNum +
                ", titleNum=" + titleNum +
                ", titleName='" + titleName + '\'' +
                ", titleDetail='" + titleDetail + '\'' +
                '}';
    }

    public long getNovelNum() {
        return novelNum;
    }

    public void setNovelNum(long novelNum) {
        this.novelNum = novelNum;
    }

    public long getTitleNum() {
        return titleNum;
    }

    public void setTitleNum(long titleNum) {
        this.titleNum = titleNum;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleDetail() {
        return titleDetail;
    }

    public void setTitleDetail(String titleDetail) {
        this.titleDetail = titleDetail;
    }
}
