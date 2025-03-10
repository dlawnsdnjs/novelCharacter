package com.example.novelcharacter.controller;

import com.example.novelcharacter.dto.UserDTO;
import com.example.novelcharacter.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(@CookieValue(value="userId", defaultValue = "None")String userId, Model model){
        model.addAttribute("userId", userId);
        
        return "index";
    }

    @PostMapping("/login")
    public String login(String site, String userId, Model model){
        UserDTO userDTO = userService.getUserById(site, userId);
        if(userDTO == null){
            return "redirect:/";
        }
        model.addAttribute("userDTO", userDTO);

        return "login";
    }


    @PostMapping("/signUp")
    public String signUp(String userId, Model model){
        model.addAttribute("userId", userId);
        return "signUp";
    }
}
