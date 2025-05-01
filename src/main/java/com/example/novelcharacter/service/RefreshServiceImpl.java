package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.RefreshDTO;
import com.example.novelcharacter.mapper.RefreshMapper;
import org.springframework.stereotype.Service;

@Service
public class RefreshServiceImpl implements RefreshService{
    private final RefreshMapper refreshMapper;

    public RefreshServiceImpl(RefreshMapper refreshMapper) {
        this.refreshMapper = refreshMapper;
    }

    @Override
    public void addRefresh(RefreshDTO refreshDTO) {
        refreshMapper.insertRefresh(refreshDTO);
    }

    @Override
    public Boolean existsByRefresh(String refresh) {
        return refreshMapper.existsByRefresh(refresh);
    }

    @Override
    public void deleteByRefresh(String refresh) {
        refreshMapper.deleteByRefresh(refresh);
    }
}
