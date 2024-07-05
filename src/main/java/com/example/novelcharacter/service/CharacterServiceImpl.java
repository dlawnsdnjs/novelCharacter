package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.CharacterDTO;
import com.example.novelcharacter.mapper.CharacterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterMapper characterMapper;

    @Autowired
    public CharacterServiceImpl(CharacterMapper characterMapper){
        this.characterMapper = characterMapper;
    }
    @Override
    public void insertCharacter(CharacterDTO characterDTO) {
        characterMapper.insertCharacter(characterDTO);
    }

    @Override
    public CharacterDTO selectCharacter(long novelNum, String characterName) {
        return characterMapper.selectCharacter(novelNum, characterName);
    }

    @Override
    public List<CharacterDTO> selectCharacterList(long novelNum) {
        return characterMapper.selectCharacterList(novelNum);
    }

    @Override
    public List<CharacterDTO> searchCharacterList(long novelNum, String character) {
        return characterMapper.searchCharacterList(novelNum, character);
    }

    @Override
    public void updateCharacter(CharacterDTO characterDTO) {
        characterMapper.updateCharacter(characterDTO);
    }

    @Override
    public int deleteCharacter(long novelNum, String CharacterName) {
        return characterMapper.deleteCharacter(novelNum, CharacterName);
    }

    @Override
    public int deleteCharacterList(long novelNum){
        return characterMapper.deleteCharacterList(novelNum);
    }
}
