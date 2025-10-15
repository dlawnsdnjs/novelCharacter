package com.example.novelcharacter.service;

import com.example.novelcharacter.JWT.JWTUtil;
import com.example.novelcharacter.dto.RefreshDTO;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p><b>ReissueService</b>는 사용자의 Refresh Token을 검증하고,
 * 유효할 경우 새로운 Access Token과 Refresh Token을 재발급하는 서비스 클래스입니다.</p>
 *
 * <p>JWT 기반 인증 구조에서, Access Token이 만료된 경우
 * Refresh Token을 통해 새로운 Access Token을 발급받는 과정을 담당합니다.</p>
 *
 * <h2>주요 기능</h2>
 * <ul>
 *     <li>Refresh Token 유효성 검증 (만료, 카테고리, 존재 여부 확인)</li>
 *     <li>새로운 Access Token / Refresh Token 재발급</li>
 *     <li>기존 Refresh Token 제거 및 새 토큰 저장</li>
 *     <li>Response Header 및 Cookie에 새 토큰 정보 추가</li>
 * </ul>
 *
 * @author
 * @version 1.0
 */
@Service
public class ReissueService {

    private final JWTUtil jwtUtil;
    private final RefreshService refreshService;

    /**
     * {@link ReissueService} 생성자.
     *
     * @param jwtUtil        JWT 관련 토큰 생성 및 검증 유틸리티
     * @param refreshService Refresh Token 데이터 관리 서비스
     */
    @Autowired
    public ReissueService(JWTUtil jwtUtil, RefreshService refreshService) {
        this.jwtUtil = jwtUtil;
        this.refreshService = refreshService;
    }

    /**
     * <p>HTTP 요청에 포함된 Refresh Token을 검증한 뒤,
     * 유효한 경우 새로운 Access Token과 Refresh Token을 발급하여 응답에 추가합니다.</p>
     *
     * <p>동작 순서:</p>
     * <ol>
     *     <li>쿠키에서 Refresh Token 추출</li>
     *     <li>Refresh Token 존재 여부 확인</li>
     *     <li>토큰 만료 검증 및 카테고리 검증</li>
     *     <li>DB에 저장된 Refresh Token 유효성 검증</li>
     *     <li>새로운 JWT(Access / Refresh) 발급</li>
     *     <li>기존 Refresh Token 삭제 후 새로 저장</li>
     *     <li>Response Header 및 Cookie에 새로운 토큰 추가</li>
     * </ol>
     *
     * @param request  클라이언트의 HTTP 요청 (Refresh Token 쿠키 포함)
     * @param response 서버의 HTTP 응답 (새 Access Token 및 쿠키 포함)
     * @return 발급 성공 시 200 OK, 실패 시 400 BAD_REQUEST 상태의 {@link ResponseEntity}
     */
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("reissue");
        String refresh = null;

        // 요청 쿠키에서 refresh token 찾기
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refresh")) {
                refresh = cookie.getValue();
            }
        }

        // Refresh Token이 없는 경우
        if (refresh == null) {
            return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
        }

        // Refresh Token 만료 여부 확인
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            return new ResponseEntity<>("refresh token expired", HttpStatus.BAD_REQUEST);
        }

        // Refresh Token 카테고리 검증
        String category = jwtUtil.getCategory(refresh);
        if (!category.equals("refresh")) {
            return new ResponseEntity<>("refresh token not valid", HttpStatus.BAD_REQUEST);
        }

        // DB에서 Refresh Token 존재 여부 확인
        Boolean isExist = refreshService.existsByRefresh(refresh);
        if (!isExist) {
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        // 토큰 정보 추출
        long uuid = jwtUtil.getUuid(refresh);
        String username = jwtUtil.getUsername(refresh);
        String role = jwtUtil.getRole(refresh);

        // 새로운 토큰 발급
        String newAccess = jwtUtil.createJwt("access", uuid, username, role, 600000L);     // 10분
        String newRefresh = jwtUtil.createJwt("refresh", uuid, username, role, 86400000L); // 24시간

        // 기존 Refresh Token 삭제 후 새로 저장
        refreshService.deleteByRefresh(refresh);
        addRefreshEntity(uuid, newRefresh, 86400000L);

        // 응답에 새 토큰 추가
        response.setHeader("access", newAccess);
        response.addCookie(createCookie("refresh", newRefresh));

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    /**
     * 새로운 Refresh Token을 쿠키로 생성합니다.
     *
     * @param key   쿠키 이름 (일반적으로 "refresh")
     * @param value 쿠키 값 (JWT Refresh Token)
     * @return 보안 속성이 설정된 {@link Cookie} 객체
     */
    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60); // 1일 유효
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        // cookie.setPath("/");
        return cookie;
    }

    /**
     * 새로운 Refresh Token 정보를 DB에 저장합니다.
     *
     * @param uuid       사용자 UUID
     * @param newRefresh 새로 발급된 Refresh Token 문자열
     * @param expires    만료 시간 (밀리초 단위)
     */
    private void addRefreshEntity(long uuid, String newRefresh, Long expires) {
        Date date = new Date(System.currentTimeMillis() + expires);

        RefreshDTO refreshEntity = new RefreshDTO();
        refreshEntity.setUuid(uuid);
        refreshEntity.setRefresh(newRefresh);
        refreshEntity.setExpiration(date.toString());

        refreshService.addRefresh(refreshEntity);
    }
}
