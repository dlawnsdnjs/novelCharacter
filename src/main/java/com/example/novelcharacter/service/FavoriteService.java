package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.FavoriteDTO;

public interface FavoriteService {
    public void setFavorite(FavoriteDTO favoriteDTO);
    public void deleteFavorite(FavoriteDTO favoriteDTO);
}
