package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.CharacterEquipDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CharacterEquipMapper {
    public CharacterEquipDTO selectCharacterEquipByIds(long novelNum, long episodeNum, long characterNum, long equipmentNum);
    public List<CharacterEquipDTO> selectCharacterEquipsByIds(long novelNum, long episodeNum, long characterNum);
    public void insertCharacterEquip(CharacterEquipDTO characterEquipDTO);
    public void deleteCharacterEquip(CharacterEquipDTO characterEquipDTO);
}
