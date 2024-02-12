package com.example.novelcharacter.dto;

public class ChangeStatDTO {
    private long changeCode;
    private long statCode;
    private long value;

    @Override
    public String toString() {
        return "ChangeStatDTO{" +
                "changeCode=" + changeCode +
                ", statCode=" + statCode +
                ", value=" + value +
                '}';
    }

    public long getChangeCode() {
        return changeCode;
    }

    public void setChangeCode(long changeCode) {
        this.changeCode = changeCode;
    }

    public long getStatCode() {
        return statCode;
    }

    public void setStatCode(long statCode) {
        this.statCode = statCode;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
