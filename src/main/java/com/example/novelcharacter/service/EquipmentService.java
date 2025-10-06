package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.*;
import com.example.novelcharacter.mapper.EquipmentMapper;
import com.example.novelcharacter.mapper.EquipmentStatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EquipmentService{
    EquipmentMapper equipmentMapper;
    EquipmentStatMapper equipmentStatMapper;
    StatService statService;
    NovelService novelService;

    @Autowired
    public EquipmentService(EquipmentMapper equipmentMapper, EquipmentStatMapper equipmentStatMapper, StatService statService, NovelService novelService) {
        this.equipmentMapper = equipmentMapper;
        this.equipmentStatMapper = equipmentStatMapper;
        this.statService = statService;
        this.novelService = novelService;
    }

    
    public EquipmentDTO selectEquipmentById(long equipmentNum){
        return equipmentMapper.selectEquipmentById(equipmentNum);
    }

    
    public void checkEquipmentOwner(long uuid, long equipmentNum) throws NoPermissionException {
        if(equipmentMapper.checkEquipmentOwner(uuid, equipmentNum) != 1){
            throw new NoPermissionException("해당 유저의 장비가 아닙니다.");
        }
    }



    
    public List<EquipmentDTO> selectEquipmentsByIds(List<Long> equipmentIds) {
        return equipmentMapper.selectEquipmentsByIds(equipmentIds);
    }

    
    public List<EquipmentDTO> selectEquipmentsByNovel(long novelNum, long uuid) throws NoPermissionException {
        novelService.checkOwner(novelNum, uuid);
        return equipmentMapper.selectEquipmentsById(novelNum);
    }

    
    public List<EquipmentDTO> selectEquipmentsPageByNovel(long novelNum, int offset, long uuid) throws NoPermissionException {
        novelService.checkOwner(novelNum, uuid);
        return equipmentMapper.selectEquipmentsPageById(novelNum, offset);
    }

    
    public EquipmentDTO selectEquipmentByName(String equipmentName, long novelNum, long uuid) throws NoPermissionException {
        novelService.checkOwner(novelNum, uuid);
        return equipmentMapper.selectEquipmentByName(equipmentName, novelNum);
    }

    
    public void insertEquipment(EquipmentDataDTO equipmentData, long uuid) throws NoPermissionException {
        EquipmentDTO equipment = equipmentData.getEquipment();
        novelService.checkOwner(equipment.getNovelNum(), uuid);
        equipmentMapper.insertEquipment(equipment);

        insertEquipmentStatList(equipment.getEquipmentNum(), equipmentData.getEquipmentStats());
    }

    
    public void updateEquipment(EquipmentDataDTO equipmentData, long uuid) throws NoPermissionException {
        EquipmentDTO equipment = equipmentData.getEquipment();
        novelService.checkOwner(equipment.getNovelNum(), uuid);
        equipmentMapper.updateEquipment(equipment);

        // 이것도 N+1 문제로 수정해야 함
        for(EquipmentStatInfoDTO equipmentStatDTO : equipmentData.getEquipmentStats()) {
            EquipmentStatDTO equipmentStat = new EquipmentStatDTO();
            equipmentStat.setEquipmentNum(equipment.getEquipmentNum());
            StatDTO statDTO = statService.selectStat(equipmentStatDTO.getStatName());
            equipmentStat.setStatCode(statDTO.getStatCode());
            equipmentStat.setStatType(equipmentStatDTO.getType());
            equipmentStat.setValue(equipmentStatDTO.getValue());

            updateEquipmentStat(equipmentStat);
        }
    }

    
    public void deleteEquipment(EquipmentDTO equipmentDTO, long uuid) throws NoPermissionException {
        novelService.checkOwner(equipmentDTO.getNovelNum(), uuid);
        equipmentMapper.deleteEquipment(equipmentDTO.getEquipmentNum());
    }

    //장비 스탯 관련
    
    public List<EquipmentStatInfoDTO> selectEquipmentStats(long equipmentNum){
        return equipmentStatMapper.selectEquipmentStatsById(equipmentNum);
    }

    
    public List<EquipmentStatInfoWithNumDTO> selectEquipmentStatsWithNum(List<Long> equipmentIds){
        return equipmentStatMapper.selectEquipmentStatsByIds(equipmentIds);
    }

    
    public void insertEquipmentStat(EquipmentStatDTO equipmentStatDTO){
        equipmentStatMapper.insertEquipmentStat(equipmentStatDTO);
    }

    
    public void insertEquipmentStatList(long equipmentNum, List<EquipmentStatInfoDTO> equipmentStats){
        List<String> statNames = equipmentStats
                .stream()
                .map(EquipmentStatInfoDTO::getStatName)
                .collect(Collectors.toList());

        List<StatDTO> statDTOS = statService.selectStatList(statNames);
        Map<String, Long> statCodeMap = statDTOS.stream()
                .collect(Collectors.toMap(StatDTO::getStatName, StatDTO::getStatCode));

        List<EquipmentStatRequestDTO> statRequests = equipmentStats.stream()
                .map(equipStat -> {
                    Long statCode = statCodeMap.get(equipStat.getStatName());
                    if (statCode == null) {
                        throw new IllegalArgumentException("존재하지 않는 스탯: " + equipStat.getStatName());
                    }

                    StatRequestDTO statRequest = new StatRequestDTO();
                    statRequest.setStatCode(statCode);
                    statRequest.setValue(equipStat.getValue());

                    EquipmentStatRequestDTO dto = new EquipmentStatRequestDTO();
                    dto.setStat(statRequest);
                    System.out.println(equipStat.getType());
                    dto.setType(equipStat.getType());
                    return dto;
                })
                .collect(Collectors.toList());

        equipmentStatMapper.insertEquipmentStatList(equipmentNum, statRequests);
    }

    
    public void deleteEquipmentStat(EquipmentStatDTO equipmentStatDTO){
        equipmentStatMapper.deleteEquipmentStat(equipmentStatDTO);
    }

    
    public void updateEquipmentStat(EquipmentStatDTO equipmentStatDTO){
        equipmentStatMapper.updateEquipmentStat(equipmentStatDTO);
    }

    
    public EquipmentDataDTO selectEquipmentData(long uuid, long equipmentNum) throws NoPermissionException {
        checkEquipmentOwner(uuid, equipmentNum);

        EquipmentDataDTO equipmentDataDTO = new EquipmentDataDTO();
        equipmentDataDTO.setEquipment(selectEquipmentById(equipmentNum));
        equipmentDataDTO.setEquipmentStats(selectEquipmentStats(equipmentNum));
        System.out.println("equipmentData: "+equipmentDataDTO);


        return equipmentDataDTO;
    }

    
    public List<EquipmentDataDTO> selectEquipmentDataList(List<Long> equipmentIds) {
        List<EquipmentDTO> equipmentDTOS = selectEquipmentsByIds(equipmentIds);
        List<EquipmentStatInfoWithNumDTO> equipmentStatInfoWithNumDTOS = selectEquipmentStatsWithNum(equipmentIds);

        // equipmentNum 기준으로 그룹핑
        Map<Long, List<EquipmentStatInfoWithNumDTO>> statMap =
                equipmentStatInfoWithNumDTOS.stream()
                        .collect(Collectors.groupingBy(EquipmentStatInfoWithNumDTO::getEquipmentNum));


        List<EquipmentDataDTO> equipmentDataDTOS = new ArrayList<>();

        for (EquipmentDTO equipmentDTO : equipmentDTOS) {
            EquipmentDataDTO equipmentDataDTO = new EquipmentDataDTO();
            equipmentDataDTO.setEquipment(equipmentDTO);

            // 해당 장비 번호에 맞는 스탯 리스트 꺼내오기
            List<EquipmentStatInfoWithNumDTO> statWithNumList = statMap.getOrDefault(equipmentDTO.getEquipmentNum(), new ArrayList<>());

            // EquipmentStatInfoDTO로 변환
            List<EquipmentStatInfoDTO> equipmentStats = statWithNumList.stream()
                    .map(statWithNum -> {
                        EquipmentStatInfoDTO statInfo = new EquipmentStatInfoDTO();
                        statInfo.setStatName(statWithNum.getStatName());
                        statInfo.setValue(statWithNum.getValue());  // statName, value
                        statInfo.setType(statWithNum.getType());
                        return statInfo;
                    })
                    .collect(Collectors.toList());

            equipmentDataDTO.setEquipmentStats(equipmentStats);
            equipmentDataDTOS.add(equipmentDataDTO);
        }

        return equipmentDataDTOS;
    }
}
