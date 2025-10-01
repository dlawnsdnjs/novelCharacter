package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.StatDTO;

import java.util.List;

public interface StatService {
    void insertStat(StatDTO statDTO);
    List<StatDTO> insertStatList(List<String> statDTOList);
    StatDTO selectStat(long statCode);
    StatDTO selectStat(String statName);
    List<StatDTO> selectStatList(List<String> statNameList);
    void updateStat(StatDTO statDTO);
    int deleteStat(long statCode);
}
