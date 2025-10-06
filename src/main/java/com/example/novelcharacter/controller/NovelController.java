package com.example.novelcharacter.controller;

import com.example.novelcharacter.JWT.JWTUtil;
import com.example.novelcharacter.dto.NovelDTO;
import com.example.novelcharacter.dto.NovelWithFavoriteDTO;
import com.example.novelcharacter.service.FavoriteService;
import com.example.novelcharacter.service.NovelService;
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
    private final JWTUtil jwtUtil;

    @Autowired
    public NovelController(NovelService novelService, JWTUtil jwtUtil){
        this.novelService = novelService;
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
