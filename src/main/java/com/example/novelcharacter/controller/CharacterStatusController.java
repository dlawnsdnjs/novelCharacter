package com.example.novelcharacter.controller;

import com.example.novelcharacter.JWT.JWTUtil;
import com.example.novelcharacter.dto.*;
import com.example.novelcharacter.service.CharacterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NoPermissionException;
import java.util.List;

@RestController
public class CharacterStatusController {
    private final JWTUtil jwtUtil;
    private final CharacterService characterService;

    public CharacterStatusController(JWTUtil jwtUtil, CharacterService characterService) {
        this.jwtUtil = jwtUtil;
        this.characterService = characterService;
    }

    @PostMapping("/episode/addCharacterStatus")
    public void addCharacterStatus(@RequestHeader String access, CharacterRequestDataDTO characterRequestDataDTO) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);
        characterService.addCharacterData(characterRequestDataDTO, uuid);

    }

    @PostMapping("/episode/characters")
    public List<CharacterDTO> selectEpisodeCharacters(@RequestHeader String access, long episodeNum) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);
        return characterService.selectCharactersByEpisode(episodeNum, uuid);
    }

    @PostMapping("/episode/characterStatus")
    public List<CharacterResponseDataDTO> selectCharacterStats(@RequestHeader String access, EpisodeCharacterDTO episodeCharacterDTO) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);
        return null;
    }

}
