package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.AchievementEffectDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AchievementEffectMapper {
    public AchievementEffectDTO selectAchievementEffectByIds(long achievementNum, long statNum, String statType);
    public List<AchievementEffectDTO> selectAchievementEffectsByIds(long achievementNum);
    public void insertAchievementEffect(AchievementEffectDTO achievementEffectDTO);
    public void updateAchievementEffect(AchievementEffectDTO achievementEffectDTO);
    public void deleteAchievementEffect(AchievementEffectDTO achievementEffectDTO);
}
