package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.PostDTO;
import com.example.novelcharacter.dto.PostDataDTO;
import com.example.novelcharacter.dto.PostResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    public void insertPost(PostDTO postDTO);
    public List<PostDataDTO> selectPostsByBoard(long boardId, int page);
    public List<PostDataDTO> selectPostsByUserId(long uuid, long boardId, int page);
    public PostResponseDTO selectPostById(long postId);
    public void updatePost(PostDTO postDTO);
    public void deletePost(long postId);
}
