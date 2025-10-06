package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.StatDTO;
import com.example.novelcharacter.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StatService{
    private final StatMapper statMapper;

    @Autowired
    public StatService(StatMapper statMapper){
        this.statMapper = statMapper;
    }

    
    public void insertStat(StatDTO statDTO) {
        statMapper.insertStat(statDTO);
    }

    
    public List<StatDTO> insertStatList(List<String> statNameList) {
        List<StatDTO> statDTOList = statNameList.stream()
                .filter(name -> name != null && !name.trim().isEmpty())
                .map(name -> {
                    StatDTO dto = new StatDTO();
                    dto.setStatName(name);
                    return dto;
                })
                .collect(Collectors.toList());

        statMapper.insertStatList(statDTOList);

        return statDTOList;
    }

    
    public StatDTO selectStat(long statCode) {
        return statMapper.selectStat(statCode);
    }

    
    public StatDTO selectStat(String statName) {
        StatDTO statDTO = statMapper.selectStat(statName);
        if(statDTO == null){
            statDTO = new StatDTO();
            statDTO.setStatName(statName);
            insertStat(statDTO);
        }
        return statDTO;
    }

    
    public List<StatDTO> selectStatList(List<String> statNameList) {
        List<String> filteredNames = statNameList.stream()
                .filter(name -> name != null && !name.trim().isEmpty()) // ✅ 필터링
                .map(String::trim)
                .toList();

        if (filteredNames.isEmpty()) {
            return new ArrayList<>();
        }

        // 1. 기존에 존재하는 stat 조회
        List<StatDTO> statDTOList = statMapper.selectStatList(filteredNames);

        // 2. 조회된 이름들 집합으로 만들기
        Set<String> existingNames = statDTOList.stream()
                .map(StatDTO::getStatName)
                .collect(Collectors.toSet());

        // 3. 없는 이름만 추려내기
        List<String> missingNames = statNameList.stream()
                .filter(name -> !existingNames.contains(name))
                .toList();


        List<StatDTO> result = new ArrayList<>(statDTOList);

        if (!missingNames.isEmpty()) {
            System.out.println(missingNames);
            result.addAll(insertStatList(missingNames));
        }

        return result;
    }

    
    public void updateStat(StatDTO statDTO) {
        statMapper.updateStat(statDTO);
    }

    
    public int deleteStat(long statCode) {
        return statMapper.deleteStat(statCode);
    }
}
