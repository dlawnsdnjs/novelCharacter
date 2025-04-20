package com.example.novelcharacter.controller;

import com.example.novelcharacter.dto.JoinDTO;
import com.example.novelcharacter.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinController {
    private final JoinService joinService;

    @Autowired
    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

//    @PostMapping("/join")
//    public String joinProcess(JoinDTO joinDTO){
//        System.out.println(joinDTO.getName());
//        joinService.joinProcess(joinDTO);
//
//        return "ok";
//    }
}
