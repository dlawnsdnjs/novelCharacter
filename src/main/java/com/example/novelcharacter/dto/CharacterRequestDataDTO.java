package com.example.novelcharacter.dto;

import lombok.Data;

import java.util.List;

@Data
public class CharacterRequestDataDTO {
    private long novelNum;
    private long episodeNum;
    private long characterNum;

    private List<Long> equipments;
    private List<StatResponseDTO> stats;

}
