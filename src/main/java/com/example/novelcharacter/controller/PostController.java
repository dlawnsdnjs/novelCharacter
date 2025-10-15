package com.example.novelcharacter.controller;

import com.example.novelcharacter.JWT.JWTUtil;
import com.example.novelcharacter.dto.*;
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

    @GetMapping("/post/boardId={boardId}/page={page}")
    public PostPageResponseDTO selectPostsByBoardId(@PathVariable("boardId") long boardId, @PathVariable("page") int page) {
        PostPageResponseDTO result = postService.selectPostsByBoard(boardId, page);
        System.out.println("board");
        System.out.println(result);
        return result;
    }

    @GetMapping("/post/boardId={boardId}/userName={userName}/page={page}")
    public PostPageResponseDTO selectPostsByUserName(@PathVariable("boardId") long boardId, @PathVariable String userName, @PathVariable("page") int page) {
        return postService.selectPostsByUsername(userName, boardId, page);
    }

    @GetMapping("/post/boardId={boardId}/myPost/page={page}")
    public PostPageResponseDTO selectMyPostsByBoardId(@RequestHeader("access") String access, @PathVariable("boardId") long boardId, @PathVariable("page") int page) {
        long uuid = jwtUtil.getUuid(access);
        return postService.selectPostsByUuid(uuid, boardId, page);
    }

    @GetMapping("/post/postId={postId}")
    public PostResponseDTO selectPostById(@PathVariable("postId") long postId) {
        return postService.selectPostById(postId);
    }

    @PostMapping("/postAdd")
    public void postAdd(@RequestHeader("access") String access, @RequestBody PostDTO postDTO) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);
        String role = jwtUtil.getRole(access);
        postService.insertPost(postDTO, uuid, role);
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
