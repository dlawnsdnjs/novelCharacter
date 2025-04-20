package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.UserDTO;
import com.example.novelcharacter.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO getUserById(String userId) {
        return userMapper.getUser(userId);
    }

    @Override
    public boolean isExistByUserId(String userId) {
        return userMapper.isExistByUserId(userId);
    }

    @Override
    public void insertUser(UserDTO userDTO){
        userMapper.insertUser(userDTO);
    }

    @Override
    public void updateUser(UserDTO userDTO){
        userMapper.updateUser(userDTO);
    }

    @Override
    public void deleteUser(UserDTO userDTO){
        userMapper.deleteUser(userDTO);
    }
}
