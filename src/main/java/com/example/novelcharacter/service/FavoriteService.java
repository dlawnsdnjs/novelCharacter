package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.FavoriteDTO;
import com.example.novelcharacter.mapper.FavoriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService{
    private final FavoriteMapper favoriteMapper;

    @Autowired
    public FavoriteService(final FavoriteMapper favoriteMapper) {
        this.favoriteMapper = favoriteMapper;
    }

    
    public void setFavorite(FavoriteDTO favoriteDTO) {
        if(favoriteMapper.getFavorite(favoriteDTO) == null) {
            favoriteMapper.addFavorite(favoriteDTO);
        }
        else{
            deleteFavorite(favoriteDTO);
        }

    }

    
    public void deleteFavorite(FavoriteDTO favoriteDTO) {
        favoriteMapper.removeFavorite(favoriteDTO);
    }

}
