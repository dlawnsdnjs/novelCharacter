package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.ChangeListDTO;
import com.example.novelcharacter.dto.ChangeStatDTO;
import com.example.novelcharacter.dto.CharacterStatDTO;
import com.example.novelcharacter.dto.StatDTO;

import java.util.List;

public interface StatService {
    void insertStat(StatDTO statDTO);
    StatDTO selectStat(long statCode);
    void updateStat(StatDTO statDTO);
    int deleteStat(long statCode);

    public void insertCharacterStat(CharacterStatDTO characterStatDTO);
    public CharacterStatDTO selectCharacterStat(long novelNum, String characterName, long statCode);
    public List<CharacterStatDTO> selectCharacterStatList(long novelNum, String characterName);
    public void updateCharacterStat(CharacterStatDTO characterStatDTO);
    public int deleteCharacterStat(long novelNum, String characterName, long statCode);

    public void insertChangeStat(ChangeStatDTO changeStatDTO);
    public ChangeStatDTO selectChangeStat(long changeCode, long statCode);
    public List<ChangeStatDTO> selectChangeStatList(long changeCode);
    public void updateChangeStat(ChangeStatDTO changeStatDTO);
    public int deleteChangeStat(long changeCode);

    public void insertChangeList(ChangeListDTO changeListDTO);
    public List<ChangeListDTO> selectChangeList(long novelNum, String characterName, long time);
    public int deleteChangeList(ChangeListDTO changeListDTO);
}
