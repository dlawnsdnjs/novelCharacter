package com.example.novelcharacter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EquipmentStatResponseDTO {
    private StatResponseDTO stat;
    private String type;
}
