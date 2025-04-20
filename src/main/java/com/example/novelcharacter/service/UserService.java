package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.UserDTO;

public interface UserService {
    UserDTO getUserById(String userId);
    boolean isExistByUserId(String userId);
    void insertUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    void deleteUser(UserDTO userDTO);
}
