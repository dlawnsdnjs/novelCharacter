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

@RestController
public class OAuthTokenController {
    private final JWTUtil jwtUtil;
    private final RefreshService refreshService;
    private final ReissueService reissueService;

    @Autowired
    public OAuthTokenController(JWTUtil jwtUtil, RefreshService refreshService, ReissueService reissueService) {
        this.jwtUtil = jwtUtil;
        this.refreshService = refreshService;
        this.reissueService = reissueService;
    }

    @GetMapping("/api/token")
    public ResponseEntity<?> syncToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorization = null;
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            System.out.println("cookies is null");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        for(Cookie cookie : cookies) {
//            System.out.println(cookie.getName()+":"+cookie.getValue());
            if(cookie.getName().equals("Authorization")) {
                authorization = cookie.getValue();
            }
            if (cookie.getName().equals("refresh")) {
                refresh = cookie.getValue();
            }
        }

        if(authorization == null) {
            System.out.println("token null at OAuthTokenController");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String token = authorization;
        System.out.println("Authorization:"+authorization);
        response.addCookie(createCookie("Authorization", null));

        try {
            boolean isExpired = jwtUtil.isExpired(token);
            System.out.println("isExpired at OAuth: " + isExpired);
        } catch (Exception e) {
            System.out.println("access token expired at OAUTH");
            System.out.println(e);

            if(refresh != null){
                return reissueService.reissue(request, response);
            }

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");

//            PrintWriter writer = response.getWriter();
//            writer.print("{\"error\": \"TOKEN_EXPIRED\", \"message\": \"Access token has expired\"}");
//            writer.flush();

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        long uuid = jwtUtil.getUuid(token);
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);
        System.out.println("uuid: " + uuid + " username: " + username + " role: " + role);

        String access = jwtUtil.createJwt("access", uuid, username, role, 600000L);
        refresh = jwtUtil.createJwt("refresh", uuid, username, role, 86400000L);

        addRefreshEntity(uuid, refresh, 86400000L);


        response.addCookie(createCookie("refresh", refresh));

        // Set access token in header and also return as JSON
        HttpHeaders headers = new HttpHeaders();
        headers.set("Access", access);

        return ResponseEntity.ok().headers(headers).build();
    }

    private void addRefreshEntity(long uuid, String refresh, Long expiredMs) {
        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshDTO refreshDTO = new RefreshDTO();
        refreshDTO.setUuid(uuid);
        refreshDTO.setRefresh(refresh);
        refreshDTO.setExpiration(date.toString());

        refreshService.addRefresh(refreshDTO);
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

}
