package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.StatDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatMapper {
    public void insertStat(StatDTO statDTO);
    public StatDTO selectStat(long statCode);
    public void updateStat(StatDTO statDTO);
    public int deleteStat(long statCode);
}
