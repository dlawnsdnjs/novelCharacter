package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.JoinDTO;

import javax.mail.MessagingException;

public interface JoinService {
    public void joinProcess(JoinDTO joinDTO);
    public void checkDuplicatedId(String userId);
    public void sendCodeToEmail(String email) throws MessagingException;
    public boolean verifiedCode(String email, String authCode);
}
