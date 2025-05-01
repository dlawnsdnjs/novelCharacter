package com.example.novelcharacter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JoinDTO {
    private String id;
    private String email;
    private String password;
    private String authCode;
}
