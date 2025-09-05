package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.EquipmentDTO;
import com.example.novelcharacter.dto.EquipmentDataDTO;
import com.example.novelcharacter.dto.EquipmentStatDTO;

import javax.naming.NoPermissionException;
import java.util.List;

public interface EquipmentService {
//    EquipmentDTO selectEquipmentById(long equipmentNum, long uuid) throws NoPermissionException;
    List<EquipmentDTO> selectEquipmentsByNovel(long novelNum, long uuid) throws NoPermissionException;
    List<EquipmentDTO> selectEquipmentsPageByNovel(long novelNum, int offset, long uuid) throws NoPermissionException;
    EquipmentDTO selectEquipmentByName(String equipmentName, long novelNum, long uuid) throws NoPermissionException;
    EquipmentDTO insertEquipment(EquipmentDataDTO equipment, long uuid) throws NoPermissionException;
    void updateEquipment(EquipmentDataDTO equipment, long uuid) throws NoPermissionException;
    void deleteEquipment(EquipmentDTO equipmentDTO, long uuid) throws NoPermissionException;

    //장비 스탯 관련
    List<EquipmentStatDTO>selectEquipmentStats(long EquipmentNum);
    void insertEquipmentStat(EquipmentStatDTO equipmentStatDTO);
    void deleteEquipmentStat(EquipmentStatDTO equipmentStatDTO);
    void updateEquipmentStat(EquipmentStatDTO equipmentStatDTO);
}
