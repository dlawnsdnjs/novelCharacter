package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.EquipmentEffectDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EquipmentEffectMapper {
    public EquipmentEffectDTO selectEquipmentEffectByIds(long equipmentNum, long statNum, String statType);
    public List<EquipmentEffectDTO> selectEquipmentEffectsByIds(long equipmentNum);
    public void insertEquipmentEffect(EquipmentEffectDTO equipmentEffectDTO);
    public void updateEquipmentEffect(EquipmentEffectDTO equipmentEffectDTO);
    public void deleteEquipmentEffect(EquipmentEffectDTO equipmentEffectDTO);
}
