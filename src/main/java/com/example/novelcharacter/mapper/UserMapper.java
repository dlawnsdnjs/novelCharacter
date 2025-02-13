package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.UserDTO;

public interface UserMapper {
    public UserDTO getUser(long site, String userId);
    public void insertUser(UserDTO userDTO);
    public void updateUser(UserDTO userDTO);
    public void deleteUser(long site, String userId);
}
