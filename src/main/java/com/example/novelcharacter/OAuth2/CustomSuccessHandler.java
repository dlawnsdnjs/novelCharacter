package com.example.novelcharacter.OAuth2;

import com.example.novelcharacter.JWT.JWTUtil;
import com.example.novelcharacter.dto.CustomOAuth2User;
import com.example.novelcharacter.dto.UserDTO;
import com.example.novelcharacter.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JWTUtil jwtUtil;
    private final UserService userService;

    public CustomSuccessHandler(JWTUtil jwtUtil, UserService userService){
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        long userId = customUserDetails.getUuid();
        String username = customUserDetails.getName();
        UserDTO userDTO = userService.getUserByUuid(userId);
        userService.updateLastLoginTime(userDTO);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        String token = jwtUtil.createJwt(userId, username, role, 60*60*60L);

        response.addCookie(createCookie("Authorization", token));
        response.sendRedirect("http://localhost:3000/oauth/callback");
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60*60*60);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
