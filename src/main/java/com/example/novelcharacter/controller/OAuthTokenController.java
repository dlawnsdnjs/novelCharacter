package com.example.novelcharacter.controller;

import com.example.novelcharacter.JWT.JWTUtil;
import com.example.novelcharacter.dto.CustomOAuth2User;
import com.example.novelcharacter.dto.RefreshDTO;
import com.example.novelcharacter.dto.UserDTO;
import com.example.novelcharacter.service.RefreshService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class OAuthTokenController {
    private final JWTUtil jwtUtil;
    private final RefreshService refreshService;

    @Autowired
    public OAuthTokenController(JWTUtil jwtUtil, RefreshService refreshService) {
        this.jwtUtil = jwtUtil;
        this.refreshService = refreshService;
    }

    @GetMapping("/api/token")
    public ResponseEntity<Void> syncToken(HttpServletRequest request, HttpServletResponse response) {
        String authorization = null;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            System.out.println(cookie.getName());
            if(cookie.getName().equals("Authorization")) {
                authorization = cookie.getValue();
            }
        }

        if(authorization == null) {
            System.out.println("token null");

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String token = authorization;

        if(jwtUtil.isExpired(token)) {
            System.out.println("token expired");

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        String access = jwtUtil.createJwt("access", username, role, 600000L);
        String refresh = jwtUtil.createJwt("refresh", username, role, 86400000L);

        addRefreshEntity(username, refresh, 86400000L);


        response.setHeader("access", access);
        response.addCookie(createCookie("refresh", refresh));
        response.setStatus(HttpStatus.OK.value());


        return ResponseEntity.ok().build(); // 바디 없이 OK
    }

    private void addRefreshEntity(String username, String refresh, Long expiredMs) {
        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshDTO refreshDTO = new RefreshDTO();
        refreshDTO.setName(username);
        refreshDTO.setRefresh(refresh);
        refreshDTO.setExpiration(date.toString());

        refreshService.addRefresh(refreshDTO);
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        cookie.setSecure(true);
        //cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

}
