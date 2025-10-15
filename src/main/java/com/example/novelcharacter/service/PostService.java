package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.*;
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
        postDTO.setUuid(uuid);
        System.out.println(postDTO);

        postMapper.insertPost(postDTO);
    }

    public PostPageResponseDTO selectPostsByBoard(long boardId, int page) {
        PostPageResponseDTO postPageResponseDTO = new PostPageResponseDTO();
        postPageResponseDTO.setData(postMapper.selectPostsByBoard(boardId, (page-1)*20));
        postPageResponseDTO.setTotalCount(postMapper.selectPostCountByBoard(boardId));
        return postPageResponseDTO;
    }

    public PostPageResponseDTO selectPostsByUuid(long uuid, long boardId, int page) {
        PostPageResponseDTO postPageResponseDTO = new PostPageResponseDTO();
        postPageResponseDTO.setData(postMapper.selectPostsByUuid(uuid, boardId, (page-1)*20));
        postPageResponseDTO.setTotalCount(postMapper.selectPostCountByUuid(boardId, uuid));
        return postPageResponseDTO;
    }

    public PostPageResponseDTO selectPostsByUsername(String userName, long boardId, int page) {
        PostPageResponseDTO postPageResponseDTO = new PostPageResponseDTO();
        postPageResponseDTO.setData(postMapper.selectPostsByUserName(userName, boardId, (page-1)*20));
        postPageResponseDTO.setTotalCount(postMapper.selectPostCountByUserName(boardId, userName));
        return postPageResponseDTO;
    }

    public PostResponseDTO selectPostById(long postId) {
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
