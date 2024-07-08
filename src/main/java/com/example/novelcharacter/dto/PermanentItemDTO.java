package com.example.novelcharacter.dto;

public class PermanentItemDTO {
    private long novelNum;
    private long itemNum;
    private String itemName;
    private String itemDetail;

    @Override
    public String toString() {
        return "PermanentItemDTO{" +
                "novelNum=" + novelNum +
                ", itemNum=" + itemNum +
                ", itemName='" + itemName + '\'' +
                ", itemDetail='" + itemDetail + '\'' +
                '}';
    }

    public long getNovelNum() {
        return novelNum;
    }

    public void setNovelNum(long novelNum) {
        this.novelNum = novelNum;
    }

    public long getItemNum() {
        return itemNum;
    }

    public void setItemNum(long itemNum) {
        this.itemNum = itemNum;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(String itemDetail) {
        this.itemDetail = itemDetail;
    }
}
