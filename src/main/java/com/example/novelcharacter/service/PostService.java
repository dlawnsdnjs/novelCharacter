package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.BoardCategoryDTO;
import com.example.novelcharacter.dto.PostDTO;
import com.example.novelcharacter.dto.PostDataDTO;
import com.example.novelcharacter.mapper.BoardCategoryMapper;
import com.example.novelcharacter.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.List;

@Service
public class PostService {
    private final PostMapper postMapper;
    private final BoardCategoryMapper boardCategoryMapper;

    @Autowired
    public PostService(PostMapper postMapper, BoardCategoryMapper boardCategoryMapper) {
        this.postMapper = postMapper;
        this.boardCategoryMapper = boardCategoryMapper;
    }

    public void insertPost(PostDTO postDTO, long uuid, String role) throws NoPermissionException {
        if(postDTO.getBoardId() == 0 && !role.equals("ROLE_ADMIN")){
            throw new NoPermissionException("관리자 권한이 필요합니다.");
        }
        if(postDTO.getUuid() != uuid){
            postDTO.setUuid(uuid);
            System.out.println("사용자와 작성자 불일치");
        }
        postMapper.insertPost(postDTO);
    }

    public List<PostDataDTO> selectPostsByBoard(long boardId, int page) {
        return postMapper.selectPostsByBoard(boardId, page);
    }

    public PostDTO selectPostById(long postId) {
        return postMapper.selectPostById(postId);
    }

    public void updatePost(PostDTO postDTO, long uuid) throws NoPermissionException {
        if(postDTO.getUuid() != uuid){
            throw new NoPermissionException("작성자만 수정 가능합니다.");
        }
        postMapper.updatePost(postDTO);
    }

    public void deletePost(PostDTO postDTO, long uuid) throws NoPermissionException {
        if(postDTO.getUuid() != uuid){
            throw new NoPermissionException("작성자만 삭제 가능합니다.");
        }
        postMapper.deletePost(postDTO.getPostId());
    }

    public List<BoardCategoryDTO> selectAllBoardCategory() {
        return boardCategoryMapper.selectAllBoardCategory();
    }
}
