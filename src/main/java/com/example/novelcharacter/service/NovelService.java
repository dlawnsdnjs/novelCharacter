package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.NovelDTO;

import java.util.List;

public interface NovelService {
    void insertNovel(NovelDTO novelDTO);
    List<NovelDTO> selectAllNovel();
    NovelDTO selectNovelOne(long novelNum);
    List<NovelDTO> searchNovel(String search);
    void updateNovel(NovelDTO novelDTO);
    void deleteNovel(long novelNum);
}
