package com.example.novelcharacter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CharacterEquipResponseDTO {
    private String equipmentName;
    private List<StatResponseDTO> stats;
}
