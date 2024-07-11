package com.example.novelcharacter.controller;

import com.example.novelcharacter.dto.NovelDTO;
import com.example.novelcharacter.service.NovelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NovelController {
    private final NovelServiceImpl novelService;

    @Autowired
    public NovelController(NovelServiceImpl novelService){
        this.novelService = novelService;
    }
    @GetMapping("/insertNovel")
    public String insertNovel(Model model){
        model.addAttribute("novelDTO", new NovelDTO());
        return "novel/novelForm";
    }

    @PostMapping("/novel")
    public String novel(String novelTitle, String writer, Model model){
        novelService.insertNovel(novelTitle, writer);
        model.addAttribute("novelList", novelService.selectAllNovel());
        return "novel/novelList";
    }

    @PostMapping("/novelList")
    public String novelList(String search, Model model){
        model.addAttribute("novelList", novelService.searchNovel(search));
        return "novel/novelList";
    }

    @GetMapping("/novelList")
    public String novelList(Model model){
        model.addAttribute("novelList", novelService.selectAllNovel());
        return "novel/novelList";
    }


    @PostMapping("/deleteNovel")
    public String deleteNovel(long novelNum, Model model){
        novelService.deleteNovel(novelNum);
        model.addAttribute("novelList", novelService.selectAllNovel());
        return "novel/novelList";
    }
}
