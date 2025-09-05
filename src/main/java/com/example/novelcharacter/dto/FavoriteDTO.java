package com.example.novelcharacter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FavoriteDTO {
    private long uuid;
    private String targetType;
    private long targetId;
}
