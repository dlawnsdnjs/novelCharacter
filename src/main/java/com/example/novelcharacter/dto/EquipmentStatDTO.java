package com.example.novelcharacter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EquipmentStatDTO {
    private long equipmentNum;
    private long statCode;
    private String statType;
    private long value;
}
