package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.CharacterStatDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CharacterStatMapper {
    public List<String> selectCharactersByEpisode(long episodeNum);
    public List<CharacterStatDTO> selectCharacterStatsByIds(long episodeNum, long characterNum);
    public CharacterStatDTO selectCharacterStatByIds(long episodeNum, long statNum);
    public void insertCharacterStat(CharacterStatDTO characterStatDTO);
    public void updateCharacterStat(CharacterStatDTO characterStatDTO);
    public void deleteCharacterStat(CharacterStatDTO characterStatDTO);
}
