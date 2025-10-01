package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.FavoriteDTO;
import com.example.novelcharacter.dto.NovelDTO;
import com.example.novelcharacter.dto.NovelWithFavoriteDTO;
import com.example.novelcharacter.mapper.NovelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.naming.NoPermissionException;
import java.util.List;

@Service
public class NovelServiceImpl implements NovelService{
    private final NovelMapper novelMapper;
    private final FavoriteService favoriteService;

    @Autowired
    public NovelServiceImpl(NovelMapper novelMapper, FavoriteService favoriteService){
        this.novelMapper = novelMapper;
        this.favoriteService = favoriteService;
    }

    @Override
    public NovelWithFavoriteDTO insertNovel(String novelTitle, long uuid) {
        if(novelTitle == null || novelTitle.trim().isEmpty()){
            throw new IllegalArgumentException("Novel Title cannot be null or empty");
        }
        NovelDTO newNovel = new NovelDTO();
        newNovel.setNovelTitle(novelTitle);
        newNovel.setUuid(uuid);
        novelMapper.insertNovel(newNovel);

        NovelWithFavoriteDTO novelWithFavoriteDTO = new NovelWithFavoriteDTO();
        novelWithFavoriteDTO.setNovelTitle(novelTitle);
        novelWithFavoriteDTO.setUuid(uuid);
        novelWithFavoriteDTO.setNovelNum(newNovel.getNovelNum());
        novelWithFavoriteDTO.setFavorite(false);
        return novelWithFavoriteDTO;
    }

    @Override
    public List<NovelWithFavoriteDTO> selectAllNovel(long uuid) {
        return novelMapper.selectAllNovel(uuid);
    }

    @Override
    public NovelDTO selectNovelOne(long novelNum, long uuid) throws NoPermissionException {
        checkOwner(novelNum, uuid);
        return novelMapper.selectNovelById(novelNum);
    }

    @Override
    public void checkOwner(long novelNum, long uuid) throws NoPermissionException {
        if(novelMapper.checkOwner(novelNum, uuid) != 1){
            throw new NoPermissionException("해당 유저의 소설이 아닙니다.");
        }
    }

//    @Override
//    public List<NovelDTO> searchNovel(String search) {
//        return novelMapper.searchNovel(search);
//    }

    @Override
    public void setFavoriteNovel(long novelNum, long uuid) {
        FavoriteDTO favoriteDTO = new FavoriteDTO();
        favoriteDTO.setUuid(uuid);
        favoriteDTO.setTargetId(novelNum);
        favoriteDTO.setTargetType("Novel");
        favoriteService.setFavorite(favoriteDTO);
    }


    @Override
    public void updateNovel(NovelDTO novelDTO) throws NoPermissionException {
        try{
            checkOwner(novelDTO.getNovelNum(), novelDTO.getUuid());

            novelMapper.updateNovel(novelDTO);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void deleteNovel(long novelNum) {
        novelMapper.deleteNovel(novelNum);
    }
}
