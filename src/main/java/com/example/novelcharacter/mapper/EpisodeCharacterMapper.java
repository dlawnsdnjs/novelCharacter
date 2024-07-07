package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.EpisodeCharacterDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EpisodeCharacterMapper {
    public EpisodeCharacterDTO selectEpisodeCharacterByIds(EpisodeCharacterDTO episodeCharacterDTO);
    public EpisodeCharacterDTO selectEpisodeCharactersByIds(long novelNum, long episodeNum);
    public EpisodeCharacterDTO selectCharacterEpisodesById(long novelNum, long characterNum);
    public void insertEpisodeCharacter(EpisodeCharacterDTO episodeCharacter);
    public void deleteEpisodeCharacter(EpisodeCharacterDTO episodeCharacter);
}
