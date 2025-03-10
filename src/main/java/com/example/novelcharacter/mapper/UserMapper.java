package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public UserDTO getUser(String site, String userId);
    public void insertUser(UserDTO userDTO);
    public void updateUser(UserDTO userDTO);
    public void deleteUser(UserDTO userDTO);
}
