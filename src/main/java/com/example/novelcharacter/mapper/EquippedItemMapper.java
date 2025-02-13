package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.CharacterEquipDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EquippedItemMapper {
    public CharacterEquipDTO selectEquippedItemByIds(long novelNum, long episodeNum, long characterNum, long equipmentNum);
    public List<CharacterEquipDTO> selectEquippedItemsByIds(long novelNum, long episodeNum, long characterNum);
    public void insertEquippedItem(CharacterEquipDTO characterEquipDTO);
    public void deleteEquippedItem(CharacterEquipDTO characterEquipDTO);
}
