package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.EquipmentStatDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EquipmentStatMapper {
    public EquipmentStatDTO selectEquipmentStatByIds(long equipmentNum, long statCode, String statType);
    public List<EquipmentStatDTO> selectEquipmentStatsByIds(long equipmentNum);
    public void insertEquipmentStat(EquipmentStatDTO equipmentStatDTO);
    public void updateEquipmentStat(EquipmentStatDTO equipmentStatDTO);
    public void deleteEquipmentStat(EquipmentStatDTO equipmentStatDTO);
}
