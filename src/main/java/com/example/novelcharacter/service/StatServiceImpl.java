package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.StatValueDTO;
import com.example.novelcharacter.dto.StatDTO;
import com.example.novelcharacter.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatServiceImpl implements StatService{
    private final StatMapper statMapper;
    private final CharacterStatMapper characterStatMapper;
    private final ChangeStatMapper changeStatMapper;
    private final ChangeListMapper changeListMapper;

    @Autowired
    public StatServiceImpl(StatMapper statMapper, CharacterStatMapper characterStatMapper, ChangeStatMapper changeStatMapper, ChangeListMapper changeListMapper){
        this.statMapper = statMapper;
        this.characterStatMapper = characterStatMapper;
        this.changeStatMapper = changeStatMapper;
        this.changeListMapper = changeListMapper;
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

    @Override
    public void insertCharacterStat(StatValueDTO statValueDTO) {
        characterStatMapper.insertCharacterStat(statValueDTO);
    }

    @Override
    public StatValueDTO selectCharacterStat(long novelNum, String characterName, long statCode) {
        return characterStatMapper.selectCharacterStat(novelNum, characterName, statCode);
    }

    @Override
    public List<StatValueDTO> selectCharacterStatList(long novelNum, String characterName) {
        return characterStatMapper.selectCharacterStatList(novelNum, characterName);
    }

    @Override
    public void updateCharacterStat(StatValueDTO statValueDTO) {
        characterStatMapper.updateCharacterStat(statValueDTO);
    }

    @Override
    public int deleteCharacterStat(long novelNum, String characterName, long statCode) {
        return characterStatMapper.deleteCharacterStat(novelNum, characterName, statCode);
    }

    @Override
    public void insertChangeStat(ChangeStatDTO changeStatDTO) {
        changeStatMapper.insertChangeStat(changeStatDTO);
    }

    @Override
    public ChangeStatDTO selectChangeStat(long changeCode, long statCode) {
        return changeStatMapper.selectChangeStat(changeCode, statCode);
    }

    @Override
    public List<ChangeStatDTO> selectChangeStatList(long changeCode) {
        return changeStatMapper.selectChangeStatList(changeCode);
    }

    @Override
    public void updateChangeStat(ChangeStatDTO changeStatDTO) {
        changeStatMapper.updateChangeStat(changeStatDTO);
    }

    @Override
    public int deleteChangeStat(long changeCode) {
        return changeStatMapper.deleteChangeStat(changeCode);
    }

    @Override
    public void insertChangeList(ChangeListDTO changeListDTO) {
        changeListMapper.insertChangeList(changeListDTO);
    }

    @Override
    public List<ChangeListDTO> selectChangeList(long novelNum, String characterName, long time) {
        return changeListMapper.selectChangeList(novelNum, characterName, time);
    }

    @Override
    public int deleteChangeList(ChangeListDTO changeListDTO) {
        return changeListMapper.deleteChangeList(changeListDTO);
    }
}
