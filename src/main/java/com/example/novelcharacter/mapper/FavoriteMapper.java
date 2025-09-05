package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.FavoriteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FavoriteMapper {
    public void addFavorite(FavoriteDTO favoriteDTO);
    public void removeFavorite(FavoriteDTO favoriteDTO);
    public FavoriteDTO getFavorite(FavoriteDTO favoriteDTO);
}
