package com.example.novelcharacter.JWT;

import com.example.novelcharacter.dto.CustomUserDetails;
import com.example.novelcharacter.dto.UserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
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

        System.out.println("========================================");
        System.out.println("🔍 JWT Filter - Request URI: " + path);
        System.out.println("🔍 Request Method: " + request.getMethod());

        // permitAll 경로들을 필터에서 제외
        if (path.equals("/") ||
                path.startsWith("/api/") ||
                path.startsWith("/login/") ||
                path.equals("/reissue") ||
                path.startsWith("/post/")) {
            System.out.println("✅ PUBLIC PATH - Skipping JWT validation");
            System.out.println("========================================");
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("🔒 PROTECTED PATH - JWT validation required");

        String accessToken = request.getHeader("access");
        System.out.println("🔑 Access token present: " + (accessToken != null ? "YES" : "NO"));

        if (accessToken == null || accessToken.trim().isEmpty()) {
            System.out.println("❌ ERROR: NO_TOKEN");
            System.out.println("========================================");
            sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "NO_TOKEN", "Access token is missing");
            return;
        }

        try {
            boolean isExpired = jwtUtil.isExpired(accessToken);
            System.out.println("Token expired: " + isExpired);
        } catch (Exception e) {
            System.out.println("❌ ERROR: TOKEN_EXPIRED - " + e.getMessage());
            System.out.println("========================================");
            sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "TOKEN_EXPIRED", "Access token has expired");
            return;
        }

        String category = jwtUtil.getCategory(accessToken);
        if (!"access".equals(category)) {
            System.out.println("❌ ERROR: INVALID_TOKEN");
            System.out.println("========================================");
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

        System.out.println("✅ JWT validation SUCCESS");
        System.out.println("========================================");

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