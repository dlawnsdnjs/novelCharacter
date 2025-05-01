package com.example.novelcharacter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CharacterDTO {
    private long novelNum;
    private String characterName;
    private long characterNum;
}
