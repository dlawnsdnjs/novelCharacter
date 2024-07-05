package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.CharacterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CharacterMapper {
    public void insertCharacter(CharacterDTO characterDTO);
    public CharacterDTO selectCharacterById(long characterNum);
    public CharacterDTO selectCharacter(long novelNum, String characterName);
    public List<CharacterDTO> selectCharacterList(long novelNum);
    public List<CharacterDTO> searchCharacterList(long novelNum, String search);
    public void updateCharacter(CharacterDTO characterDTO);
    public int deleteCharacter(long novelNum, String characterName);
    public int deleteCharacterList(long novelNum);
}
