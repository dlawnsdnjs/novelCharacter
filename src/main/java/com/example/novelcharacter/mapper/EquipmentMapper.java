package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.EquipmentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EquipmentMapper {
    public EquipmentDTO selectEquipmentById(long equipmentNum);
    public List<EquipmentDTO> selectEquipmentsById(long novelNum);
    public List<EquipmentDTO> selectEquipmentsPageById(long novelNum, int offset);
    public EquipmentDTO selectEquipmentByName(String equipmentName, long novelNum);
    public void insertEquipment(EquipmentDTO equipment);
    public void updateEquipment(EquipmentDTO equipment);
    public void deleteEquipment(long equipmentNum);
}
