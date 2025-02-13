package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.CharacterEquipDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EquippedTitleMapper {
    public EquippedTitleDTO selectEquippedTitleByIds(EquippedTitleDTO equippedTitleDTO);
    public List<EquippedTitleDTO> selectEquippedTitlesByIds(long novelNum, long episodeNum, long characterNum);
    public void insertEquippedTitle(EquippedTitleDTO equippedTitleDTO);
    public void deleteEquippedTitle(CharacterEquipDTO characterEquipDTO);
}
