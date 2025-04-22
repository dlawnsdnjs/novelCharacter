package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.UserDTO;
import com.example.novelcharacter.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;



    @Override
    public UserDTO getUserById(String userId) {
        return userMapper.getUser(userId);
    }

    @Override
    public boolean isExistByUserId(String userId) {
        UserDTO userDTO = getUserById(userId);
        return userDTO != null;
    }

    @Override
    public UserDTO findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public boolean isExistByEmail(String email) {
        UserDTO userDTO = findByEmail(email);
        return userDTO != null;
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
