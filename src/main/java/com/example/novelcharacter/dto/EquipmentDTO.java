package com.example.novelcharacter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EquipmentDTO {
    private long novelNum;
    private long equipmentNum;
    private String equipmentName;
    private String info;
}
