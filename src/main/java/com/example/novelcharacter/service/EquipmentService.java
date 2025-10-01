package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.*;

import javax.naming.NoPermissionException;
import java.util.List;

public interface EquipmentService {
    EquipmentDTO selectEquipmentById(long equipmentNum);
    void checkEquipmentOwner(long uuid, long equipmentNum) throws NoPermissionException;
    List<EquipmentDTO> selectEquipmentsByIds(List<Long> equipmentIds);
    List<EquipmentDTO> selectEquipmentsByNovel(long novelNum, long uuid) throws NoPermissionException;
    List<EquipmentDTO> selectEquipmentsPageByNovel(long novelNum, int offset, long uuid) throws NoPermissionException;
    EquipmentDTO selectEquipmentByName(String equipmentName, long novelNum, long uuid) throws NoPermissionException;
    void insertEquipment(EquipmentDataDTO equipment, long uuid) throws NoPermissionException;
    void updateEquipment(EquipmentDataDTO equipment, long uuid) throws NoPermissionException;
    void deleteEquipment(EquipmentDTO equipmentDTO, long uuid) throws NoPermissionException;

    //장비 스탯 관련
    List<EquipmentStatInfoDTO>selectEquipmentStats(long EquipmentNum);
    List<EquipmentStatInfoWithNumDTO>selectEquipmentStatsWithNum(List<Long> equipmentIds);
    void insertEquipmentStat(EquipmentStatDTO equipmentStatDTO);
    void insertEquipmentStatList(long EquipmentNum, List<EquipmentStatInfoDTO> equipmentStatInfoDTOS);
    void deleteEquipmentStat(EquipmentStatDTO equipmentStatDTO);
    void updateEquipmentStat(EquipmentStatDTO equipmentStatDTO);

    EquipmentDataDTO selectEquipmentData(long uuid, long equipmentNum) throws NoPermissionException;
    List<EquipmentDataDTO> selectEquipmentDataList(List<Long> equipmentIds);
}
