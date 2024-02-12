package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.CharacterStatDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CharacterStatMapper {
    public void insertCharacterStat(CharacterStatDTO characterStatDTO);
    public CharacterStatDTO selectCharacterStat(long novelNum, String characterName, long statCode);
    public List<CharacterStatDTO> selectCharacterStatList(long novelNum, String characterName);
    public void updateCharacterStat(CharacterStatDTO characterStatDTO);
    public int deleteCharacterStat(long novelNum, String characterName, long statCode);
}
