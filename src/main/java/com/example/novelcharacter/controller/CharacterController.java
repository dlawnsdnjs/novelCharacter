package com.example.novelcharacter.controller;

import com.example.novelcharacter.dto.CharactersDTO;
import com.example.novelcharacter.mapper.CharactersMapper;
import com.example.novelcharacter.service.CharacterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CharacterController {
    CharacterServiceImpl characterService;
    @Autowired
    public CharacterController(CharacterServiceImpl characterService){
        this.characterService = characterService;
    }

    @PostMapping("/characterForm")
    public String characterForm(long novelNum, Model model){
        CharactersDTO charactersDTO = new CharactersDTO();
        charactersDTO.setNovelNum(novelNum);
        model.addAttribute("charactersDTO", charactersDTO);
        return "character/characterForm";
    }

    @PostMapping("/characterList")
    public String statList(long novelNum, Model model){
        model.addAttribute("novelNumber", novelNum);
        model.addAttribute("characterList", characterService.selectCharacterList(novelNum));
        return "character/characterList";
    }

    @PostMapping("/characterSearch")
    public String characterSearch(long novelNum, String search, Model model){
        model.addAttribute("novelNum", novelNum);
        model.addAttribute("search", search);
        model.addAttribute("characterList", characterService.searchCharacterList(novelNum, search));
        return "character/characterList";
    }
}
