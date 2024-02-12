package com.example.novelcharacter.controller;

import com.example.novelcharacter.dto.CharactersDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

public class StatController {
   /* @PostMapping("/statForm")
    public String insertStat(long novelNum, Model model){
        CharactersDTO charactersDTO = new CharactersDTO();
        charactersDTO.setNovelNum(novelNum);
        System.out.println("소설 번호 : "+novelNum);
        model.addAttribute("baseStatDTO", charactersDTO);
        return "stat/StatForm";
    }

    @PostMapping("/stat")
    public String stat(CharactersDTO charactersDTO, Model model){
        System.out.println(charactersDTO);
        charactersMapper.insertCharacter(charactersDTO);
        model.addAttribute("baseStatDTO", charactersDTO);
        return "stat/stat";
    }
    @PostMapping("/statDetail")
    public String statDetail(CharactersDTO charactersDTO, Model model){
        System.out.println(charactersDTO);
        model.addAttribute("baseStatDTO", charactersDTO);
        return "stat/stat";
    }*/

}
