package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.RefreshDTO;
import com.example.novelcharacter.mapper.RefreshMapper;
import org.springframework.stereotype.Service;

@Service
public class RefreshService{
    private final RefreshMapper refreshMapper;

    public RefreshService(RefreshMapper refreshMapper) {
        this.refreshMapper = refreshMapper;
    }

    
    public void addRefresh(RefreshDTO refreshDTO) {
        refreshMapper.insertRefresh(refreshDTO);
    }

    
    public Boolean existsByRefresh(String refresh) {
        return refreshMapper.existsByRefresh(refresh);
    }

    
    public void deleteByRefresh(String refresh) {
        refreshMapper.deleteByRefresh(refresh);
    }
}
