package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.StatDTO;

public interface StatService {
    void insertStat(StatDTO statDTO);
    StatDTO selectStat(long statCode);
    void updateStat(StatDTO statDTO);
    int deleteStat(long statCode);
}
