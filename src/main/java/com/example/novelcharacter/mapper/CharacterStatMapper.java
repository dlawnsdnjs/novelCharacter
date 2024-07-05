package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.StatValueDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CharacterStatMapper {
    public void insertCharacterStat(StatValueDTO statValueDTO);
    public StatValueDTO selectCharacterStat(long novelNum, String characterName, long statCode);
    public List<StatValueDTO> selectCharacterStatList(long novelNum, String characterName);
    public void updateCharacterStat(StatValueDTO statValueDTO);
    public int deleteCharacterStat(long novelNum, String characterName, long statCode);
}
