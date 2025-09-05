package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.NovelDTO;
import com.example.novelcharacter.dto.NovelWithFavoriteDTO;

import javax.naming.NoPermissionException;
import java.util.List;

public interface NovelService {
    NovelWithFavoriteDTO insertNovel(String novelTitle, long writer);
    List<NovelWithFavoriteDTO> selectAllNovel(long uuid);
    NovelDTO selectNovelOne(long novelNum, long uuid) throws NoPermissionException;
    void checkOwner(long novelNum, long uuid) throws NoPermissionException;
//    List<NovelDTO> searchNovel(String search);
    void setFavoriteNovel(long novelNum, long uuid);
    void updateNovel(NovelDTO novelDTO) throws NoPermissionException;
    void deleteNovel(long novelNum);
}
