package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.EquippedItemDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EquippedItemMapper {
    public EquippedItemDTO selectEquippedItemByIds(long novelNum, long episodeNum, long characterNum, long equipmentNum);
    public List<EquippedItemDTO> selectEquippedItemsByIds(long novelNum, long episodeNum, long characterNum);
    public void insertEquippedItem(EquippedItemDTO equippedItemDTO);
    public void deleteEquippedItem(EquippedItemDTO equippedItemDTO);
}
