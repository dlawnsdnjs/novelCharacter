package com.example.novelcharacter.controller;

import com.example.novelcharacter.JWT.JWTUtil;
import com.example.novelcharacter.dto.FavoriteDTO;
import com.example.novelcharacter.dto.NovelDTO;
import com.example.novelcharacter.dto.NovelWithFavoriteDTO;
import com.example.novelcharacter.service.FavoriteService;
import com.example.novelcharacter.service.NovelService;
import com.example.novelcharacter.service.NovelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NoPermissionException;
import java.util.List;
import java.util.Map;

@RestController
public class NovelController {
    private final NovelService novelService;
    private final FavoriteService favoriteService;
    private final JWTUtil jwtUtil;

    @Autowired
    public NovelController(NovelServiceImpl novelService, FavoriteService favoriteService, JWTUtil jwtUtil){
        this.novelService = novelService;
        this.favoriteService = favoriteService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/insertNovel")
    public NovelWithFavoriteDTO insertNovel(
            @RequestHeader("access") String access,
            @RequestBody Map<String, String> payload) {

        long uuid = jwtUtil.getUuid(access);

        String title = payload.get("title");
        System.out.println("uuid: " + uuid);
        System.out.println("title: " + title);

        return novelService.insertNovel(title, uuid);
    }

    @PostMapping("/setFavoriteNovel")
    public void setFavoriteNovel(@RequestHeader("access") String access, @RequestBody Map<String, Long> payload){
        long uuid = jwtUtil.getUuid(access);
        novelService.setFavoriteNovel(payload.get("novelNum"), uuid);
    }

    @GetMapping("/novel/{novelNum}")
    public ResponseEntity<?> getNovel(@RequestHeader("access") String access, @PathVariable("novelNum") long novelNum) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);
        try {
            NovelDTO novel = novelService.selectNovelOne(novelNum, uuid);
            return ResponseEntity.status(HttpStatus.OK).body(novel);
        }
        catch(Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

    }

//    @PostMapping("/novel")
//    public String novel(String novelTitle, long writer,  Model model){
//        novelService.insertNovel(novelTitle, writer);
//        model.addAttribute("novelList", novelService.selectAllNovel());
//        return "novel/novelList";
//    }

//    @PostMapping("/novelList")
//    public String novelList(String search, Model model){
//        model.addAttribute("novelList", novelService.searchNovel(search));
//        return "novel/novelList";
//    }

    @GetMapping("/novelList")
    public List<NovelWithFavoriteDTO> novelList(
            @RequestHeader("access") String access) {

        long uuid = jwtUtil.getUuid(access);

        System.out.println(novelService.selectAllNovel(uuid));

        return novelService.selectAllNovel(uuid);
    }


    @PostMapping("/deleteNovel")
    public ResponseEntity<Void> deleteNovel(@RequestHeader String access, @RequestBody  Map<String, Long> payload){
        novelService.deleteNovel(payload.get("novelNum"));
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
