package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.BoardCategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCategoryMapper {
    public void insertBoardCategory(BoardCategoryDTO boardCategoryDTO);
    public List<BoardCategoryDTO> selectAllBoardCategory();
    public void updateBoardCategory(BoardCategoryDTO boardCategoryDTO);
    public void deleteBoardCategory(long boardId);
}
