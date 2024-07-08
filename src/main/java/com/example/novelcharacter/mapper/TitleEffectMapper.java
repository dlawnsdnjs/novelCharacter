package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.TitleEffectDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TitleEffectMapper {
    public TitleEffectDTO selectTitleEffectByIds(long titleNum, long statNum, String statType);
    public List<TitleEffectDTO> selectTitleEffectsByIds(long titleNum);
    public void insertTitleEffect(TitleEffectDTO titleEffectDTO);
    public void updateTitleEffect(TitleEffectDTO titleEffectDTO);
    public void deleteTitleEffect(TitleEffectDTO titleEffectDTO);
}
