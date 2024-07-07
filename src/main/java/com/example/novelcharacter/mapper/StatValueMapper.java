package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.StatValueDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatValueMapper {
    public StatValueDTO selectStatValuesByIds(long novelNum, long episodeNum);
    public StatValueDTO selectStatValueByIds(long novelNum, long episodeNum, long statNum);
    public void insertStatValue(StatValueDTO statValueDTO);
    public void updateStatValue(StatValueDTO statValueDTO);
    public void deleteStatValue(StatValueDTO statValueDTO);
}
