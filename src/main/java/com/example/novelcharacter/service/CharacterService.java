package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.CharacterDTO;

import java.util.List;

public interface CharacterService {
    void insertCharacter(CharacterDTO characterDTO);
    CharacterDTO selectCharacter(long novelNum, String characterName);
    List<CharacterDTO> selectCharacterList(long novelNum);
    List<CharacterDTO> searchCharacterList(long novelNum, String character);
    void updateCharacter(CharacterDTO characterDTO);
    int deleteCharacter(long novelNum, String CharacterName);
    int deleteCharacterList(long novelNum);
}
