package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public UserDTO getUserByUuid(long uuid);
    public UserDTO getUserById(String userId);
    public UserDTO getUserByName(String uesrName);
    public UserDTO findByEmail(String email);
    public void insertUser(UserDTO userDTO);
    public void updateUser(UserDTO userDTO);
    public void deleteUser(UserDTO userDTO);
}
