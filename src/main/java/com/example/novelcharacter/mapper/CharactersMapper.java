package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.CharactersDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CharactersMapper {
    public void insertCharacter(CharactersDTO charactersDTO);
    public CharactersDTO selectCharacter(long novelNum, String characterName);
    public List<CharactersDTO> selectCharacterList(long novelNum);
    public List<CharactersDTO> searchCharacterList(long novelNum, String search);
    public void updateCharacter(CharactersDTO charactersDTO);
    public int deleteCharacter(long novelNum, String characterName);
    public int deleteCharacterList(long novelNum);
}
