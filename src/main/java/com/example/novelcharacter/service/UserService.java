package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.UserDTO;
import com.example.novelcharacter.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService{
    private final UserMapper userMapper;

    
    public UserDTO getUserByUuid(long uuid){
        return userMapper.getUserByUuid(uuid);
    }

    
    public UserDTO getUserById(String userId) {
        return userMapper.getUserById(userId);
    }

    public UserDTO getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }
    
    public boolean isExistByUserId(String userId) {
        UserDTO userDTO = getUserById(userId);
        return userDTO != null;
    }

    
    public UserDTO findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    
    public boolean isExistByEmail(String email) {
        UserDTO userDTO = findByEmail(email);
        return userDTO != null;
    }

    
    public void insertUser(UserDTO userDTO){
        userMapper.insertUser(userDTO);
    }

    
    public void updateUser(UserDTO userDTO){
        userMapper.updateUser(userDTO);
    }

    public void updateUserName(String userName, long uuid) throws Exception {
        UserDTO userDTO = getUserByUuid(uuid);
        if(getUserByName(userName) != null){
            throw new DuplicateMemberException("중복된 이름입니다");
        }
        userDTO.setUsername(userName);
        updateUser(userDTO);
    }

    public void updateLastLoginTime(UserDTO userDTO){
        userDTO.setLastLoginDate(LocalDate.now());
        updateUser(userDTO);
    }

    
    public void deleteUser(UserDTO userDTO){
        userMapper.deleteUser(userDTO);
    }

}
