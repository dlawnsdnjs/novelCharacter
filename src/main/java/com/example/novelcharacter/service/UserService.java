package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.UserDTO;

import javax.mail.MessagingException;

public interface UserService {
    UserDTO getUserById(String userId);
    boolean isExistByUserId(String userId);
    UserDTO findByEmail(String email);
    boolean isExistByEmail(String email);
    void insertUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    void deleteUser(UserDTO userDTO);

}
