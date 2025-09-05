package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.EpisodeDTO;

import javax.naming.NoPermissionException;
import java.util.List;

public interface EpisodeService {
    public EpisodeDTO insertEpisode(EpisodeDTO episodeDTO, long uuid) throws NoPermissionException;
    public List<EpisodeDTO> selectAllEpisode(long novelNum, long uuid) throws NoPermissionException;
    public List<EpisodeDTO> selectEpisodePage(long novelNum, int offset, long uuid) throws NoPermissionException;
//    public void rebalanceOrderIndex(long novelNum);
//    public EpisodeDTO selectEpisodeById(long episodeNum, long uuid);
    public int checkEpisodeOwner(long episodeNum, long uuid) throws NoPermissionException;
    public List<EpisodeDTO> searchEpisode(String search, long novelNum, long uuid) throws NoPermissionException;
    public void updateEpisode(EpisodeDTO episodeDTO, long uuid) throws NoPermissionException;
    public void deleteEpisode(long episodeNum, long uuid) throws NoPermissionException;
}
