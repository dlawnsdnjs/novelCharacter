package com.example.novelcharacter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NovelDTO {
    private long novelNum;
    private String novelTitle;
    private long uuid;
}
