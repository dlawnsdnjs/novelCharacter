package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.StatDTO;
import com.example.novelcharacter.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatServiceImpl implements StatService{
    private final StatMapper statMapper;

    @Autowired
    public StatServiceImpl(StatMapper statMapper){
        this.statMapper = statMapper;
    }
    @Override
    public void insertStat(StatDTO statDTO) {
        statMapper.insertStat(statDTO);
    }

    @Override
    public StatDTO selectStat(long statCode) {
        return statMapper.selectStat(statCode);
    }

    @Override
    public void updateStat(StatDTO statDTO) {
        statMapper.updateStat(statDTO);
    }

    @Override
    public int deleteStat(long statCode) {
        return statMapper.deleteStat(statCode);
    }
}
