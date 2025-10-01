package com.example.novelcharacter.controller;

import com.example.novelcharacter.JWT.JWTUtil;
import com.example.novelcharacter.dto.*;
import com.example.novelcharacter.service.CharacterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NoPermissionException;
import java.util.List;
import java.util.Map;

@RestController
public class CharacterStatusController {
    private final JWTUtil jwtUtil;
    private final CharacterService characterService;

    public CharacterStatusController(JWTUtil jwtUtil, CharacterService characterService) {
        this.jwtUtil = jwtUtil;
        this.characterService = characterService;
    }

    @PostMapping("/episode/addCharacterStatus")
    public void addCharacterStatus(@RequestHeader String access, @RequestBody CharacterRequestDataDTO characterRequestDataDTO) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);
        System.out.println("characterData:"+characterRequestDataDTO);
        characterService.addCharacterData(characterRequestDataDTO, uuid);

    }

    @PostMapping("/episode/characters")
    public List<CharacterDTO> selectEpisodeCharacters(@RequestHeader String access, @RequestBody Map<String, Long> payload) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);
        long episodeNum = payload.get("episodeNum");
        System.out.println("episodeNum:"+episodeNum);
        return characterService.selectCharactersByEpisode(episodeNum, uuid);
    }

    @PostMapping("/episode/characterStatus")
    public CharacterResponseDataDTO selectCharacterStats(@RequestHeader String access, @RequestBody EpisodeCharacterDTO episodeCharacterDTO) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);
        System.out.println("uuid:"+uuid);
        System.out.println("episodeCharacterDTO"+episodeCharacterDTO);
        System.out.println(characterService.selectCharacterData(episodeCharacterDTO, uuid));
        return characterService.selectCharacterData(episodeCharacterDTO, uuid);
    }

}
