package com.example.novelcharacter.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AchievementMapper {
    public AchievementDTO selectAchievementById(long achievementNum);
    public List<AchievementDTO> selectAchievementsById(long novelNum);
    public void insertAchievement(AchievementDTO achievementDTO);
    public void updateAchievement(AchievementDTO achievementDTO);
    public void deleteAchievement(AchievementDTO achievementDTO);
}
