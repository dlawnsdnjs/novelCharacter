package com.example.novelcharacter.controller;

import com.example.novelcharacter.JWT.JWTUtil;
import com.example.novelcharacter.dto.EpisodeDTO;
import com.example.novelcharacter.service.EpisodeService;
import com.example.novelcharacter.service.NovelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NoPermissionException;
import java.util.List;
import java.util.Map;

@RestController
public class EpisodeController {
    private final EpisodeService episodeService;
    private final JWTUtil jwtUtil;

    @Autowired
    public EpisodeController(EpisodeService episodeService, JWTUtil jwtUtil) {
        this.episodeService = episodeService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/allEpisode")
    public List<EpisodeDTO> getAllEpisode(@RequestHeader String access, @RequestBody Map<String, Long> payload) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);
        long novelNum = payload.get("novelNum");
        System.out.println("All Episode:"+episodeService.selectAllEpisode(novelNum, uuid));


        return episodeService.selectAllEpisode(novelNum, uuid);
    }

    @PostMapping("/addEpisode")
    public EpisodeDTO addEpisode(@RequestHeader String access, @Valid @RequestBody EpisodeDTO episodeDTO) throws NoPermissionException {
//        EpisodeDTO newEpisode = new EpisodeDTO();
        long uuid = jwtUtil.getUuid(access);
        episodeService.insertEpisode(episodeDTO, uuid);

        System.out.println("episodeDTO:"+episodeDTO);


        return episodeDTO;
    }

    @PostMapping("/deleteEpisode")
    public ResponseEntity<Void> deleteEpisode(@RequestHeader String access, @RequestBody EpisodeDTO episodeDTO) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);
        episodeService.deleteEpisode(episodeDTO.getEpisodeNum(), uuid);

        return ResponseEntity.noContent().build();
    }
}
