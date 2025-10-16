package com.example.novelcharacter.controller;

import com.example.novelcharacter.JWT.JWTUtil;
import com.example.novelcharacter.dto.RefreshDTO;
import com.example.novelcharacter.service.RefreshService;
import com.example.novelcharacter.service.ReissueService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

/**
 * OAuth 로그인 이후 액세스 토큰과 리프레시 토큰을 동기화하고,
 * 토큰이 만료된 경우 자동으로 재발급하는 역할을 담당하는 컨트롤러입니다.
 *
 * <p>이 컨트롤러는 클라이언트가 쿠키로 전달한 JWT 토큰을 검증하고,
 * 필요 시 새로운 토큰을 발급하여 응답 헤더 및 쿠키에 재설정합니다.</p>
 *
 * <ul>
 *     <li>요청 경로: <b>/api/token</b></li>
 *     <li>역할: OAuth 로그인 후 토큰 검증 및 재발급</li>
 *     <li>사용 서비스:
 *         <ul>
 *             <li>{@link JWTUtil} — JWT 생성 및 검증</li>
 *             <li>{@link RefreshService} — 리프레시 토큰 저장/관리</li>
 *             <li>{@link ReissueService} — 토큰 재발급 로직 처리</li>
 *         </ul>
 *     </li>
 * </ul>
 */
@RestController
public class OAuthTokenController {

    private final JWTUtil jwtUtil;
    private final RefreshService refreshService;
    private final ReissueService reissueService;

    /**
     * {@code OAuthTokenController} 생성자.
     *
     * @param jwtUtil         JWT 관련 유틸리티 클래스
     * @param refreshService  리프레시 토큰 관리 서비스
     * @param reissueService  토큰 재발급 처리 서비스
     */
    @Autowired
    public OAuthTokenController(JWTUtil jwtUtil, RefreshService refreshService, ReissueService reissueService) {
        this.jwtUtil = jwtUtil;
        this.refreshService = refreshService;
        this.reissueService = reissueService;
    }

    /**
     * 클라이언트 쿠키에 저장된 JWT 토큰을 검증하고,
     * 만료되었을 경우 리프레시 토큰으로 새로운 액세스 토큰을 발급합니다.
     *
     * <p>처리 절차:</p>
     * <ol>
     *     <li>요청 쿠키에서 Authorization, refresh 토큰을 추출</li>
     *     <li>액세스 토큰이 만료되지 않았다면 새로운 토큰을 재발급</li>
     *     <li>만료되었다면 {@link ReissueService}를 통해 재발급 수행</li>
     *     <li>새로운 액세스/리프레시 토큰을 쿠키 및 헤더에 추가하여 반환</li>
     * </ol>
     *
     * @param request  {@link HttpServletRequest} — 클라이언트 요청 객체 (쿠키 포함)
     * @param response {@link HttpServletResponse} — 응답 객체 (쿠키/헤더 설정용)
     * @return {@link ResponseEntity} — 토큰 상태 및 재발급 결과를 포함한 응답
     * @throws IOException 응답 스트림 조작 중 예외 발생 시
     */
    @GetMapping("/api/token")
    public ResponseEntity<?> syncToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorization = null;
        String refresh = null;
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            System.out.println("cookies is null");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // 쿠키에서 Authorization, refresh 토큰 추출
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Authorization")) {
                authorization = cookie.getValue();
            }
            if (cookie.getName().equals("refresh")) {
                refresh = cookie.getValue();
            }
        }

        if (authorization == null) {
            System.out.println("token null at OAuthTokenController");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String token = authorization;
        System.out.println("Authorization:" + authorization);

        // 기존 Authorization 쿠키 제거
        response.addCookie(createCookie("Authorization", null));

        try {
            boolean isExpired = jwtUtil.isExpired(token);
            System.out.println("isExpired at OAuth: " + isExpired);
        } catch (Exception e) {
            System.out.println("access token expired at OAUTH");
            System.out.println(e);

            // 액세스 토큰 만료 시 리프레시 토큰으로 재발급 시도
            if (refresh != null) {
                return reissueService.reissue(request, response);
            }

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // 액세스 토큰이 유효한 경우 새로운 토큰 생성
        long uuid = jwtUtil.getUuid(token);
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        System.out.println("uuid: " + uuid + " username: " + username + " role: " + role);

        String access = jwtUtil.createJwt("access", uuid, username, role, 600000L);
        refresh = jwtUtil.createJwt("refresh", uuid, username, role, 86400000L);

        addRefreshEntity(uuid, refresh, 86400000L);

        response.addCookie(createCookie("refresh", refresh));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Access", access);

        return ResponseEntity.ok().headers(headers).build();
    }

    /**
     * 리프레시 토큰 정보를 DB에 저장합니다.
     *
     * @param uuid      사용자 고유 식별자
     * @param refresh   발급된 리프레시 토큰 문자열
     * @param expiredMs 만료 시간(밀리초 단위)
     */
    private void addRefreshEntity(long uuid, String refresh, Long expiredMs) {
        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshDTO refreshDTO = new RefreshDTO();
        refreshDTO.setUuid(uuid);
        refreshDTO.setRefresh(refresh);
        refreshDTO.setExpiration(date.toString());

        refreshService.addRefresh(refreshDTO);
    }

    /**
     * 쿠키를 생성하는 유틸리티 메서드입니다.
     *
     * <p>생성된 쿠키는 다음 속성을 가집니다:</p>
     * <ul>
     *     <li>Secure: true</li>
     *     <li>HttpOnly: true</li>
     *     <li>Path: "/"</li>
     *     <li>유효기간: 1일 (24시간)</li>
     * </ul>
     *
     * @param key   쿠키 이름
     * @param value 쿠키 값 (null일 경우 쿠키 삭제로 간주)
     * @return 생성된 {@link Cookie} 객체
     */
    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
