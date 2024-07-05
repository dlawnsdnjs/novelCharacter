package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.EpisodeDTO;
import com.example.novelcharacter.dto.NovelDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EpisodeMapper {
    public void insertEpisode(EpisodeDTO episodeDTO);
    public List<EpisodeDTO> selectAllEpisode(long novelNum);
    public NovelDTO selectEpisodeById(long episodeNum);
    public List<NovelDTO> searchEpisode(String search);
    public void updateEpisode(EpisodeDTO episodeDTO);
    public void deleteEpisode(long episodeNum);
}
