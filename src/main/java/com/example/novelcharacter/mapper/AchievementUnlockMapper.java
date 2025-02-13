package com.example.novelcharacter.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AchievementUnlockMapper {
    public void insertAchievementUnlock(AchievementUnlockDTO achievementUnlockDTO);
    public AchievementUnlockDTO selectAchievementUnlockByIds(long novelNum, long episodeNum, long characterNum, long achievementNum);
    public List<AchievementUnlockDTO> selectAchievementUnlocksByIds(long novelNum, long episodeNum, long characterNum);
    public void deleteAchievementUnlock(AchievementUnlockDTO achievementUnlockDTO);

}
