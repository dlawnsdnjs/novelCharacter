package com.example.novelcharacter.dto;

import lombok.Data;

import java.util.List;

@Data
public class EquipmentDataDTO {
    private EquipmentDTO equipment;

    private List<EquipmentStatResponseDTO> equipmentStats;
}

