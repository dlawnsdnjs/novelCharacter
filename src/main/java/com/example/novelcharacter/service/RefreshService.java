package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.RefreshDTO;
import jakarta.transaction.Transactional;

public interface RefreshService {
    void addRefresh(RefreshDTO refreshDTO);

    Boolean existsByRefresh(String refresh);

    @Transactional
    void deleteByRefresh(String refresh);
}
