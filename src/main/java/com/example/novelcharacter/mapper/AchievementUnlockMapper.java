package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.AchievementDTO;
import com.example.novelcharacter.dto.AchievementEffectDTO;
import com.example.novelcharacter.dto.AchievementUnlockDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AchievementUnlockMapper {
    public AchievementUnlockDTO selectAchievementUnlockByIds(long novelNum, long episodeNum, long characterNum, long achievementNum);
    public List<AchievementUnlockDTO> selectAchievementUnlocksByIds(long novelNum, long episodeNum, long characterNum);
    public void insertAchievementUnlock(AchievementUnlockDTO achievementUnlockDTO);
    public void deleteAchievementUnlock(AchievementUnlockDTO achievementUnlockDTO);

}
