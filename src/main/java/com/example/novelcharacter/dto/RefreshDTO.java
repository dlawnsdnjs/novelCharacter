package com.example.novelcharacter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RefreshDTO {
    private Long id;
    private String name;
    private String refresh;
    private String expiration;
}
