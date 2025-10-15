package com.example.novelcharacter.controller;

import com.example.novelcharacter.JWT.JWTUtil;
import com.example.novelcharacter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    private final UserService userService;
    private final JWTUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, JWTUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/userUpdate")
    public void userUpdate(@RequestHeader("access") String access, @RequestBody Map<String, String> body) throws Exception {
        long uuid = jwtUtil.getUuid(access);
        String userName = body.get("username");
        userService.updateUserName(userName,uuid);
    }
}
