package com.example.novelcharacter.controller;

import com.example.novelcharacter.dto.JoinDTO;
import com.example.novelcharacter.dto.UserDTO;
import com.example.novelcharacter.service.JoinService;
import com.example.novelcharacter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;
    private final JoinService joinService;

    @Autowired
    public UserController(UserService userService, JoinService joinService) {
        this.userService = userService;
        this.joinService = joinService;
    }


    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO){
        System.out.println(joinDTO.getName());
        joinService.joinProcess(joinDTO);

        return "ok";
    }
}
