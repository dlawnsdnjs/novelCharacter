package com.example.novelcharacter.controller;

import com.example.novelcharacter.service.ReissueService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReissueController {
    private final ReissueService reissueService;

    @Autowired
    public ReissueController(ReissueService reissueService) {
        this.reissueService = reissueService;
    }

    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        return reissueService.reissue(request, response);
    }
}
