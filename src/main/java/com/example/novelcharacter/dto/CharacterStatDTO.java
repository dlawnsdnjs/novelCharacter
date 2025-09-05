package com.example.novelcharacter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CharacterStatDTO {
    private long episodeNum;
    private long characterNum;
    private long statCode;
    private long value;
}
