package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.FavoriteDTO;
import com.example.novelcharacter.mapper.FavoriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteMapper favoriteMapper;

    @Autowired
    public FavoriteServiceImpl(final FavoriteMapper favoriteMapper) {
        this.favoriteMapper = favoriteMapper;
    }

    @Override
    public void setFavorite(FavoriteDTO favoriteDTO) {
        if(favoriteMapper.getFavorite(favoriteDTO) == null) {
            favoriteMapper.addFavorite(favoriteDTO);
        }
        else{
            deleteFavorite(favoriteDTO);
        }

    }

    @Override
    public void deleteFavorite(FavoriteDTO favoriteDTO) {
        favoriteMapper.removeFavorite(favoriteDTO);
    }

}
