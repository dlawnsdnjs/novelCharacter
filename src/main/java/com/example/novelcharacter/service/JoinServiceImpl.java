package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.JoinDTO;
import com.example.novelcharacter.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinServiceImpl implements JoinService {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public JoinServiceImpl(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void joinProcess(JoinDTO joinDTO) {
        String username = joinDTO.getName();
        String password = joinDTO.getPassword();
        String email = joinDTO.getEmail();

        Boolean isExist = userService.isExistByUserId(username);

        if (isExist) {
            return;
        }

        UserDTO data = new UserDTO();
        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setEmail(email);
        data.setRole("ROLE_USER");

        userService.insertUser(data);
    }
}
