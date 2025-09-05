package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.CharacterStatDTO;
import com.example.novelcharacter.dto.EpisodeCharacterDTO;
import com.example.novelcharacter.dto.StatResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CharacterStatMapper {
    public List<CharacterStatDTO> selectCharacterStatsByIds(long episodeNum, long characterNum);
    public List<StatResponseDTO> selectCharacterStatsResponse(EpisodeCharacterDTO episodeCharacterDTO);
    public void insertCharacterStat(CharacterStatDTO characterStatDTO);
    public void updateCharacterStat(CharacterStatDTO characterStatDTO);
    public void deleteCharacterStat(CharacterStatDTO characterStatDTO);
}
