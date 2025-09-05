package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.EpisodeDTO;
import com.example.novelcharacter.mapper.EpisodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.List;

@Service
public class EpisodeServiceImpl implements EpisodeService {
    private final EpisodeMapper episodeMapper;
    private final NovelService novelService;

    @Autowired
    public EpisodeServiceImpl(EpisodeMapper episodeMapper, NovelService novelService) {
        this.episodeMapper = episodeMapper;
        this.novelService = novelService;
    }

    @Override
    public EpisodeDTO insertEpisode(EpisodeDTO episodeDTO, long uuid) throws NoPermissionException {
        novelService.checkOwner(episodeDTO.getNovelNum(), uuid);
        episodeMapper.insertEpisode(episodeDTO);
        episodeMapper.updateOrderIndexNull(episodeDTO);
        return episodeDTO;
    }

    @Override
    public List<EpisodeDTO> selectAllEpisode(long novelNum, long uuid) throws NoPermissionException {
        novelService.checkOwner(novelNum, uuid);

        return episodeMapper.selectAllEpisode(novelNum);
    }

    @Override
    public List<EpisodeDTO> selectEpisodePage(long novelNum, int offset, long uuid) throws NoPermissionException {
        novelService.checkOwner(novelNum, uuid);

        return episodeMapper.selectEpisodePage(novelNum, offset);
    }

    // 리밸런싱 작업은 프론트측에서 작업하고 백엔드에서는 업데이트만 동작하는게 이상적
//    @Override
//    public void rebalanceOrderIndex(long novelNum) {
//        episodeMapper.rebalanceOrderIndex(novelNum);
//    }

//    @Override
//    public EpisodeDTO selectEpisodeById(long episodeNum, long uuid) {
////        novelService.checkOwner(novelNum, uuid);
//
//        return episodeMapper.selectEpisodeById(episodeNum);
//    }


    @Override
    public int checkEpisodeOwner(long episodeNum, long uuid) throws NoPermissionException {
        if(episodeMapper.checkEpisodeOwner(episodeNum,uuid) == 1){
            return 1;
        }
        else{
            throw new NoPermissionException("사용자가 작성한 회차가 아닙니다.");
        }
    }

    @Override
    public List<EpisodeDTO> searchEpisode(String search, long novelNum, long uuid) throws NoPermissionException {
        novelService.checkOwner(novelNum, uuid);
        return episodeMapper.searchEpisode(search);
    }

    @Override
    public void updateEpisode(EpisodeDTO episodeDTO, long uuid) throws NoPermissionException {
        novelService.checkOwner(episodeDTO.getNovelNum(), uuid);
        episodeMapper.updateEpisode(episodeDTO);
    }

    @Override
    public void deleteEpisode(long episodeNum, long uuid) throws NoPermissionException {
        checkEpisodeOwner(episodeNum, uuid);
        episodeMapper.deleteEpisode(episodeNum);
    }
}
