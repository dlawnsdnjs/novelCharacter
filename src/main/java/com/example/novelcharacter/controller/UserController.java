package com.example.novelcharacter.controller;

import com.example.novelcharacter.JWT.JWTUtil;
import com.example.novelcharacter.service.UserService;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 사용자 정보 관련 요청을 처리하는 REST 컨트롤러입니다.
 *
 * <p>JWT 토큰을 이용해 인증된 사용자의 정보를 변경하는 기능을 제공합니다.</p>
 * <p>현재는 사용자 이름(username) 변경 기능만 포함되어 있습니다.</p>
 */
@RestController
public class UserController {

    private final UserService userService;
    private final JWTUtil jwtUtil;

    /**
     * {@code UserController} 생성자.
     *
     * @param userService 사용자 관련 데이터베이스 작업을 처리하는 서비스
     * @param jwtUtil     JWT 토큰의 검증 및 사용자 식별을 담당하는 유틸리티 클래스
     */
    @Autowired
    public UserController(UserService userService, JWTUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 사용자 이름을 변경합니다.
     *
     * <p>클라이언트에서 전달된 Access Token을 검증하여 UUID를 추출하고,
     * 해당 사용자의 이름을 요청 본문에 포함된 새로운 이름으로 업데이트합니다.</p>
     *
     * @param access JWT Access Token (요청 헤더에서 전달)
     * @param body   새로운 사용자 이름을 포함한 요청 본문 (예: {"username": "newName"})
     * @throws Exception 이름이 중복되거나 변경 권한이 없을 경우 발생
     */
    @PatchMapping("/userUpdate")
    public void userUpdate(@RequestHeader("access") String access, @RequestBody Map<String, String> body) throws DuplicateMemberException {
        long uuid = jwtUtil.getUuid(access);
        String userName = body.get("userName");
        userService.updateUserName(userName, uuid);
    }
}
