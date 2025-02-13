package com.example.novelcharacter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/")
    public String index(@CookieValue(value="userId", defaultValue = "None")String userId, Model model){
        model.addAttribute("userId", userId);

        return "index";
    }
}
