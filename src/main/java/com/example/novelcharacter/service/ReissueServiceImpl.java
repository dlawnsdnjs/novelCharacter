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

@Service
public class ReissueServiceImpl implements ReissueService {
    private final JWTUtil jwtUtil;
    private final RefreshService refreshService;

    @Autowired
    public ReissueServiceImpl(JWTUtil jwtUtil, RefreshService refreshService) {
        this.jwtUtil = jwtUtil;
        this.refreshService = refreshService;
    }

    @Override
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("reissue");
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("refresh")) {
                refresh = cookie.getValue();
            }
        }

        if(refresh == null){
            return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
        }

        try{
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e){
            return new ResponseEntity<>("refresh token expired", HttpStatus.BAD_REQUEST);
        }

        String category = jwtUtil.getCategory(refresh);

        if(!category.equals("refresh")){
            return new ResponseEntity<>("refresh token not valid", HttpStatus.BAD_REQUEST);
        }

        Boolean isExist = refreshService.existsByRefresh(refresh);
        if (!isExist) {

            //response body
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }
        long uuid = jwtUtil.getUuid(refresh);
        String username = jwtUtil.getUsername(refresh);
        String role = jwtUtil.getRole(refresh);

        String newAccess = jwtUtil.createJwt("access", uuid, username, role, 600000L);
        String newRefresh = jwtUtil.createJwt("refresh", uuid, username, role, 86400000L);

        refreshService.deleteByRefresh(refresh);
        addRefreshEntity(uuid, newRefresh, 86400000L);

        response.setHeader("access", newAccess);
        response.addCookie(createCookie("refresh", newRefresh));

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        cookie.setSecure(true);
        //cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

    private void addRefreshEntity(long uuid, String newRefresh, Long expires) {
        Date date = new Date(System.currentTimeMillis() + expires);

        RefreshDTO refreshEntity = new RefreshDTO();
        refreshEntity.setUuid(uuid);
        refreshEntity.setRefresh(newRefresh);
        refreshEntity.setExpiration(date.toString());

        refreshService.addRefresh(refreshEntity);
    }
}
