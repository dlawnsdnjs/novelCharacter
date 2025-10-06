package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.JoinDTO;
import com.example.novelcharacter.dto.UserDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class JoinService {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final String AUTH_CODE_PREFIX = "AuthCode ";
    private final EmailService mailService;
    private final RedisService redisService;

    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;


    
    public void joinProcess(JoinDTO joinDTO) { // ResponseEntity로 각 분기별 코드 보내야 할 듯
        System.out.println("joinDTO: " + joinDTO.toString());
        String username = joinDTO.getId();
        String password = joinDTO.getPassword();
        String email = joinDTO.getEmail();
        String authCode = joinDTO.getAuthCode();

        this.checkDuplicatedId(username);
        this.checkDuplicatedEmail(email);
        String authComplete = redisService.getValues(authCode);
        System.out.println("authComplete: "+authComplete);

        if(authComplete.equals("ok")) {
            UserDTO data = new UserDTO();
            data.setUserId(username);
            data.setUsername(username);
            data.setPassword(bCryptPasswordEncoder.encode(password));
            data.setEmail(email);
            data.setRole("ROLE_USER");
            data.setLastLoginDate(LocalDate.now());

            userService.insertUser(data);
            System.out.println("가입 성공: "+data);
        }

        return;
    }

    public void checkDuplicatedId(String userId){
        boolean user = userService.isExistByUserId(userId);
        if (user) {
            log.debug("JoinServiceImpl.checkDuplicatedId exception occur email: {}", userId);
            throw new RuntimeException("JoinServiceImpl.checkDuplicatedId exception occur email: " + userId);
        }
    }


    public void sendCodeToEmail(String toEmail) throws MessagingException {
        this.checkDuplicatedEmail(toEmail);
        String title = "Travel with me 이메일 인증 번호";
        String authCode = this.createCode();
        mailService.sendEmail(toEmail, title, authCode);
        // 이메일 인증 요청 시 인증 번호 Redis에 저장 ( key = "AuthCode " + Email / value = AuthCode )
        redisService.setValues(AUTH_CODE_PREFIX + toEmail,
                authCode, Duration.ofMillis(this.authCodeExpirationMillis));
    }

    private void checkDuplicatedEmail(String email) {
        boolean user = userService.isExistByEmail(email);
        if (user) {
            log.debug("JoinServiceImpl.checkDuplicatedEmail exception occur email: {}", email);
            throw new RuntimeException("JoinServiceImpl.checkDuplicatedEmail exception occur email: " + email);
        }
    }

    private String createCode() {
        int length = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            log.debug("userService.createCode() exception occur");
            throw new RuntimeException("userService.createCode() exception occur");
        }
    }

    public boolean verifiedCode(String email, String authCode) {
        this.checkDuplicatedEmail(email);
        String redisAuthCode = redisService.getValues(AUTH_CODE_PREFIX + email);

        System.out.println(email);
        System.out.println(authCode);

        boolean result = redisService.checkExistsValue(redisAuthCode) && redisAuthCode.equals(authCode);
        System.out.println("result: "+ result);

        if(result){
            redisService.setValues(authCode+email, "ok", Duration.ofMillis(authCodeExpirationMillis));
            System.out.println("authEmail: "+ authCode+email);
        }

        return result;
    }

}
