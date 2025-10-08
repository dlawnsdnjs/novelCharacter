package com.example.novelcharacter.controller;

import com.example.novelcharacter.JWT.JWTUtil;
import com.example.novelcharacter.dto.BoardCategoryDTO;
import com.example.novelcharacter.dto.PostDTO;
import com.example.novelcharacter.dto.PostDataDTO;
import com.example.novelcharacter.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.NoPermissionException;
import java.util.List;

@RestController
public class PostController {
    private final PostService postService;
    private final JWTUtil jwtUtil;

    @Autowired
    public PostController(PostService postService, JWTUtil jwtUtil) {
        this.postService = postService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/board")
    public List<BoardCategoryDTO> selectAllBoard(){
        return postService.selectAllBoardCategory();
    }

    @GetMapping("/boardId={boardId}/page={page}")
    public List<PostDataDTO> selectPostsByBoardId(@RequestHeader("access") String access, @PathVariable("boardId") long boardId, @PathVariable("page") int page) {
        long uuid = jwtUtil.getUuid(access);
        return postService.selectPostsByBoard(boardId, page);
    }

    @GetMapping("/postId={postId}")
    public PostDTO selectPostById(@RequestHeader("access") String access, @PathVariable("postId") long postId) {
        long uuid = jwtUtil.getUuid(access);
        return postService.selectPostById(postId);
    }

    @PostMapping("/postUpdate")
    public void updatePost(@RequestHeader("access") String access, @RequestBody PostDTO postDTO) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);
        postService.updatePost(postDTO, uuid);
    }

    @PostMapping("/postDelete")
    public void deletePost(@RequestHeader("access") String access, @RequestBody PostDTO postDTO) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);
        postService.deletePost(postDTO, uuid);
    }
}
