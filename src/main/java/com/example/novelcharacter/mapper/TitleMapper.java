package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.TitleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TitleMapper {
    public TitleDTO selectTitleById(long titleNum);
    public List<TitleDTO> selectTitlesById(long novelNum);
    public void insertTitle(TitleDTO titleDTO);
    public void updateTitle(TitleDTO titleDTO);
    public void deleteTitle(TitleDTO titleDTO);
}
