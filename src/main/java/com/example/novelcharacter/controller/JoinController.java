package com.example.novelcharacter.controller;

import com.example.novelcharacter.dto.JoinDTO;
import com.example.novelcharacter.service.JoinService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api")
public class JoinController {
    private final JoinService joinService;

    @Autowired
    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String joinProcess(@RequestBody JoinDTO joinDTO) {
//        System.out.println(joinDTO.getId());
        joinService.joinProcess(joinDTO);

        return "ok";
    }

    @PostMapping("/emailVerify")
    public ResponseEntity<String> sendVerificationEmail(@RequestBody EmailRequest request) throws MessagingException {
        String email = request.getEmail();
        System.out.println("이메일 인증 요청: " + email);
        joinService.sendCodeToEmail(email);
        // 이메일 전송 로직 (서비스 호출 등)

        // 예시 응답
        return ResponseEntity.ok("ok");
    }


//    @GetMapping("/emailVerify")
//    public ResponseEntity<String> testEmail() throws MessagingException {
//        System.out.println("이메일 인증 요청: ");
//        // 이메일 전송 로직 (서비스 호출 등)
//
//        // 예시 응답
//        return ResponseEntity.ok("ok");
//    }


    @PostMapping("/codeVerify")
    public String codeVerify(@RequestBody CodeRequest request) {
        String email = request.getEmail();
        String code = request.getCode();
        boolean result = joinService.verifiedCode(email, code);
        if (result) {
            return "ok";
        }
        return "code incorrect";
    }

    @Data
    public static class EmailRequest {
        private String email;
    }

    @Data
    public static class CodeRequest {
        private String email;
        private String code;
    }
}
