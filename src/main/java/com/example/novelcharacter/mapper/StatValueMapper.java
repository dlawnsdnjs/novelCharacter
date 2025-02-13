package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.CharacterStatDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatValueMapper {
    public CharacterStatDTO selectStatValuesByIds(long novelNum, long episodeNum);
    public CharacterStatDTO selectStatValueByIds(long novelNum, long episodeNum, long statNum);
    public void insertStatValue(CharacterStatDTO characterStatDTO);
    public void updateStatValue(CharacterStatDTO characterStatDTO);
    public void deleteStatValue(CharacterStatDTO characterStatDTO);
}
