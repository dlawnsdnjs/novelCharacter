package com.example.novelcharacter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshDTO {
    private Long id;
    private String name;
    private String refresh;
    private String expiration;
}
