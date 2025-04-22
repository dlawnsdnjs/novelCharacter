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
import java.util.Random;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class JoinServiceImpl implements JoinService {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final String AUTH_CODE_PREFIX = "AuthCode ";
    private final EmailService mailService;
    private final RedisService redisService;

    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;


    @Override
    public void joinProcess(JoinDTO joinDTO) {
        String username = joinDTO.getName();
        String password = joinDTO.getPassword();
        String email = joinDTO.getEmail();

        boolean isExist = userService.isExistByUserId(username);

        if (isExist) {
            return;
        }

        isExist = userService.isExistByEmail(email);
        if (isExist) {
            return;
        }
        String authComplete = redisService.getValues(email);

        if(authComplete.equals("ok")) {
            UserDTO data = new UserDTO();
            data.setUsername(username);
            data.setPassword(bCryptPasswordEncoder.encode(password));
            data.setEmail(email);
            data.setRole("ROLE_USER");


            userService.insertUser(data);
        }

        return;
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
        UserDTO user = userService.findByEmail(email);
        if (user != null) {
            log.debug("MemberServiceImpl.checkDuplicatedEmail exception occur email: {}", email);
            throw new RuntimeException("MemberServiceImpl.checkDuplicatedEmail exception occur email: " + email);
        }
    }

    private String createCode() {
        int lenth = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < lenth; i++) {
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

        boolean result = redisService.checkExistsValue(redisAuthCode) && redisAuthCode.equals(authCode);

        if(result){
            redisService.setValues(email, "ok", Duration.ofMillis(authCodeExpirationMillis));
        }

        return result;
    }

}
