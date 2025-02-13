package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.NovelDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NovelMapper {
    public void insertNovel(String novelTitle, long uuid);
    public List<NovelDTO> selectAllNovel();
    public NovelDTO selectNovelById(long novelNum);
    public List<NovelDTO> searchNovel(String search);
    public void updateNovel(NovelDTO novelDTO);
    public void deleteNovel(long novelNum);
}
