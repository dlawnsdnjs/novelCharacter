package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.CharactersDTO;

import java.util.List;

public interface CharacterService {
    void insertCharacter(CharactersDTO charactersDTO);
    CharactersDTO selectCharacter(long novelNum, String characterName);
    List<CharactersDTO> selectCharacterList(long novelNum);
    List<CharactersDTO> searchCharacterList(long novelNum, String character);
    void updateCharacter(CharactersDTO charactersDTO);
    int deleteCharacter(long novelNum, String CharacterName);
    int deleteCharacterList(long novelNum);
}
