package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.EquipmentDTO;
import com.example.novelcharacter.dto.EquipmentDataDTO;
import com.example.novelcharacter.dto.EquipmentStatDTO;
import com.example.novelcharacter.dto.StatDTO;
import com.example.novelcharacter.mapper.EquipmentMapper;
import com.example.novelcharacter.mapper.EquipmentStatMapper;
import com.example.novelcharacter.mapper.StatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService{
    EquipmentMapper equipmentMapper;
    EquipmentStatMapper equipmentStatMapper;
    StatMapper statMapper;
    NovelService novelService;

    @Autowired
    public EquipmentServiceImpl(EquipmentMapper equipmentMapper, EquipmentStatMapper equipmentStatMapper, StatMapper statMapper, NovelService novelService) {
        this.equipmentMapper = equipmentMapper;
        this.equipmentStatMapper = equipmentStatMapper;
        this.statMapper = statMapper;
        this.novelService = novelService;
    }

//    @Override
//    public EquipmentDTO selectEquipmentById(long equipmentNum, long uuid) throws NoPermissionException {
//        EquipmentDTO equipmentDTO = equipmentMapper.selectEquipmentById(equipmentNum);
//        novelService.checkOwner(equipmentDTO.getNovelNum(), uuid);
//        return equipmentDTO;
//    }

    @Override
    public List<EquipmentDTO> selectEquipmentsByNovel(long novelNum, long uuid) throws NoPermissionException {
        novelService.checkOwner(novelNum, uuid);
        return equipmentMapper.selectEquipmentsById(novelNum);
    }

    @Override
    public List<EquipmentDTO> selectEquipmentsPageByNovel(long novelNum, int offset, long uuid) throws NoPermissionException {
        novelService.checkOwner(novelNum, uuid);
        return equipmentMapper.selectEquipmentsPageById(novelNum, offset);
    }

    @Override
    public EquipmentDTO selectEquipmentByName(String equipmentName, long novelNum, long uuid) throws NoPermissionException {
        novelService.checkOwner(novelNum, uuid);
        return equipmentMapper.selectEquipmentByName(equipmentName, novelNum);
    }

    @Override
    public EquipmentDTO insertEquipment(EquipmentDataDTO equipment, long uuid) throws NoPermissionException {
        novelService.checkOwner(equipment.getNovelNum(), uuid);
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        equipmentDTO.setEquipmentName(equipment.getEquipmentName());
        equipmentDTO.setInform(equipment.getInform());

        equipmentMapper.insertEquipment(equipmentDTO);
        // equipmentStat 추가해야 함
        for(EquipmentDataDTO.EquipmentStatRequestDTO equipmentStatDTO : equipment.getEquipmentStats()) {
            EquipmentStatDTO equipmentStat = new EquipmentStatDTO();
            equipmentStat.setEquipmentNum(equipmentDTO.getEquipmentNum());
            StatDTO statDTO = statMapper.selectStat(equipmentStatDTO.getStatName());
            equipmentStat.setStatCode(statDTO.getStatCode());
            equipmentStat.setStatType(equipmentStatDTO.getType());
            equipmentStat.setValue(equipmentStatDTO.getValue());

            insertEquipmentStat(equipmentStat);
        }


        return equipmentDTO;
    }

    @Override
    public void updateEquipment(EquipmentDataDTO equipment, long uuid) throws NoPermissionException {
        novelService.checkOwner(equipment.getNovelNum(), uuid);
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        equipmentDTO.setNovelNum(equipment.getNovelNum());
        equipmentDTO.setEquipmentName(equipment.getEquipmentName());
        equipmentDTO.setEquipmentName(equipment.getEquipmentName());
        equipmentDTO.setInform(equipment.getInform());
        equipmentMapper.updateEquipment(equipmentDTO);

        for(EquipmentDataDTO.EquipmentStatRequestDTO equipmentStatDTO : equipment.getEquipmentStats()) {
            EquipmentStatDTO equipmentStat = new EquipmentStatDTO();
            equipmentStat.setEquipmentNum(equipmentDTO.getEquipmentNum());
            StatDTO statDTO = statMapper.selectStat(equipmentStatDTO.getStatName());
            equipmentStat.setStatCode(statDTO.getStatCode());
            equipmentStat.setStatType(equipmentStatDTO.getType());
            equipmentStat.setValue(equipmentStatDTO.getValue());

            updateEquipmentStat(equipmentStat);
        }
    }

    @Override
    public void deleteEquipment(EquipmentDTO equipmentDTO, long uuid) throws NoPermissionException {
        novelService.checkOwner(equipmentDTO.getNovelNum(), uuid);
        equipmentMapper.deleteEquipment(equipmentDTO.getEquipmentNum());
    }

    //장비 스탯 관련
    @Override
    public List<EquipmentStatDTO> selectEquipmentStats(long equipmentNum){
        return equipmentStatMapper.selectEquipmentStatsByIds(equipmentNum);
    }

    @Override
    public void insertEquipmentStat(EquipmentStatDTO equipmentStatDTO){
        equipmentStatMapper.insertEquipmentStat(equipmentStatDTO);
    }

    @Override
    public void deleteEquipmentStat(EquipmentStatDTO equipmentStatDTO){
        equipmentStatMapper.deleteEquipmentStat(equipmentStatDTO);
    }

    @Override
    public void updateEquipmentStat(EquipmentStatDTO equipmentStatDTO){
        equipmentStatMapper.updateEquipmentStat(equipmentStatDTO);
    }
}
