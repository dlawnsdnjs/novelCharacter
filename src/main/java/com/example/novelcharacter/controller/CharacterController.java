package com.example.novelcharacter.controller;

import com.example.novelcharacter.JWT.JWTUtil;
import com.example.novelcharacter.dto.CharacterDTO;
import com.example.novelcharacter.dto.EpisodeDTO;
import com.example.novelcharacter.service.CharacterService;
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
public class CharacterController {
    CharacterService characterService;
    EpisodeService episodeService;
    NovelService novelService;
    JWTUtil jwtUtil;

    @Autowired
    public CharacterController(CharacterService characterService, NovelService novelService, EpisodeService episodeService, JWTUtil jwtUtil){
        this.characterService = characterService;
        this.novelService = novelService;
        this.episodeService = episodeService;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/episodeCharacters")
    public List<CharacterDTO> episodeCharacter(@RequestHeader String access, @RequestBody long episodeNum) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);

        return characterService.selectCharactersByEpisode(episodeNum, uuid);
    }

    @PostMapping("/novelCharacters")
    public List<CharacterDTO> novelCharacters(@RequestHeader String access, @RequestBody Map<String, Long> payload) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);
        long novelNum = payload.get("novelNum");
        System.out.println("characterList");
        System.out.println(characterService.selectCharacterList(novelNum, uuid));


        return characterService.selectCharacterList(novelNum, uuid);
    }

    @PostMapping("/addCharacter")
    public CharacterDTO addCharacter(@RequestHeader String access,@Valid @RequestBody CharacterDTO characterDTO) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);
        characterService.insertCharacter(characterDTO, uuid);

        return characterDTO;
    }

    @PostMapping("/deleteCharacter")
    public ResponseEntity<Void> deleteEpisode(@RequestHeader String access, @RequestBody CharacterDTO characterDTO) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);
        characterService.deleteCharacter(characterDTO, uuid);


        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/characterSearch")
//    public String characterSearch(long novelNum, String search, Model model){
//        model.addAttribute("novelNum", novelNum);
//        model.addAttribute("search", search);
//        model.addAttribute("characterList", characterService.searchCharacterList(novelNum, search));
//        return "character/characterList";
//    }
}
