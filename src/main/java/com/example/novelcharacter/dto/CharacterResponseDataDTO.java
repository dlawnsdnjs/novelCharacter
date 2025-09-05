package com.example.novelcharacter.dto;

import lombok.Data;

import java.util.List;

@Data
public class CharacterResponseDataDTO {
    private CharacterDTO character;
    private List<StatResponseDTO> stats;
    private List<EquipmentDataDTO> equipment;
    private List<StatResponseDTO> finalStats;
}
