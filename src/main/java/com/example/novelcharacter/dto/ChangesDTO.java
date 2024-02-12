package com.example.novelcharacter.dto;

public class ChangesDTO {
    private long changeCode;
    private String changeName;
    private String detail;

    @Override
    public String toString() {
        return "ChangesDTO{" +
                "changeCode=" + changeCode +
                ", changeName='" + changeName + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }

    public long getChangeCode() {
        return changeCode;
    }

    public void setChangeCode(long changeCode) {
        this.changeCode = changeCode;
    }

    public String getChangeName() {
        return changeName;
    }

    public void setChangeName(String changeName) {
        this.changeName = changeName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
