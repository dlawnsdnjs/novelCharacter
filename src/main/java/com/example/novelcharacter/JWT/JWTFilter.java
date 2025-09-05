package com.example.novelcharacter.JWT;

import com.example.novelcharacter.dto.CustomOAuth2User;
import com.example.novelcharacter.dto.CustomUserDetails;
import com.example.novelcharacter.dto.UserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String path = request.getRequestURI();
        System.out.println("Path: "+path);
        if (path.equals("/api/token") || path.equals("/reissue")) {
            System.out.println("Pass URL");
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = request.getHeader("access");
        if (accessToken == null || accessToken.trim().isEmpty()) {
            sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "NO_TOKEN", "Access token is missing");
            return;
        }

        try {
            boolean isExpired = jwtUtil.isExpired(accessToken);
            System.out.println("isExpired: " + isExpired);
        } catch (Exception e) {
            System.out.println("access token expired");
            System.out.println(e);
            sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "TOKEN_EXPIRED", "Access token has expired");
            return;
        }

        String category = jwtUtil.getCategory(accessToken);
        if (!"access".equals(category)) {
            sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "INVALID_TOKEN", "Invalid access token");
            return;
        }

        long uuid = jwtUtil.getUuid(accessToken);
        String username = jwtUtil.getUsername(accessToken);
        String role = jwtUtil.getRole(accessToken);

        UserDTO user = new UserDTO();
        user.setUuid(uuid);
        user.setUsername(username);
        user.setRole(role);

        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

    private void sendError(HttpServletResponse response, int status, String error, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print("{\"error\": \"" + error + "\", \"message\": \"" + message + "\"}");
        writer.flush();
    }
}