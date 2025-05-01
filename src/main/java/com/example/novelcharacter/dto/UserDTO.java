package com.example.novelcharacter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UserDTO {
    private long uuid;
    private String userId;
    private String username;
    private String email;
    private String password;
    private String role;
    private LocalDate lastLoginDate;
}
