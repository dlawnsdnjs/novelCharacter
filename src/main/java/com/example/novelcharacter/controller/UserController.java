package com.example.novelcharacter.controller;

import com.example.novelcharacter.dto.JoinDTO;
import com.example.novelcharacter.service.JoinService;
import com.example.novelcharacter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserController {
    private final UserService userService;


}
