package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.CharactersDTO;
import com.example.novelcharacter.mapper.CharactersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharactersMapper charactersMapper;

    @Autowired
    public CharacterServiceImpl(CharactersMapper charactersMapper){
        this.charactersMapper = charactersMapper;
    }
    @Override
    public void insertCharacter(CharactersDTO charactersDTO) {
        charactersMapper.insertCharacter(charactersDTO);
    }

    @Override
    public CharactersDTO selectCharacter(long novelNum, String characterName) {
        return charactersMapper.selectCharacter(novelNum, characterName);
    }

    @Override
    public List<CharactersDTO> selectCharacterList(long novelNum) {
        return charactersMapper.selectCharacterList(novelNum);
    }

    @Override
    public List<CharactersDTO> searchCharacterList(long novelNum, String character) {
        return charactersMapper.searchCharacterList(novelNum, character);
    }

    @Override
    public void updateCharacter(CharactersDTO charactersDTO) {
        charactersMapper.updateCharacter(charactersDTO);
    }

    @Override
    public int deleteCharacter(long novelNum, String CharacterName) {
        return charactersMapper.deleteCharacter(novelNum, CharacterName);
    }

    @Override
    public int deleteCharacterList(long novelNum){
        return charactersMapper.deleteCharacterList(novelNum);
    }
}
