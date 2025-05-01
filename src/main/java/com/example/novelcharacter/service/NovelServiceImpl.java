package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.NovelDTO;
import com.example.novelcharacter.mapper.NovelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NovelServiceImpl implements NovelService{
    private final NovelMapper novelMapper;

    @Autowired
    public NovelServiceImpl(NovelMapper novelMapper){
        this.novelMapper = novelMapper;
    }

    @Override
    public void insertNovel(String novelTitle, long writer) {
        novelMapper.insertNovel(novelTitle, writer);
    }

    @Override
    public List<NovelDTO> selectAllNovel() {
        return novelMapper.selectAllNovel();
    }

    @Override
    public NovelDTO selectNovelOne(long novelNum) {
        return novelMapper.selectNovelById(novelNum);
    }

    @Override
    public List<NovelDTO> searchNovel(String search) {
        return novelMapper.searchNovel(search);
    }

    @Override
    public void updateNovel(NovelDTO novelDTO) {
        novelMapper.updateNovel(novelDTO);
    }

    @Override
    public void deleteNovel(long novelNum) {
        novelMapper.deleteNovel(novelNum);
    }
}
