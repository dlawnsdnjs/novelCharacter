package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.*;

import javax.naming.NoPermissionException;
import java.util.List;

public interface CharacterService {
    void insertCharacter(CharacterDTO characterDTO, long uuid) throws NoPermissionException;
    CharacterDTO selectCharacter(long characterNum);
    void checkCharacterOwner(long uuid, long characterNum) throws NoPermissionException;
//    List<CharacterDTO> selectAllCharacters(long uuid);
    List<CharacterDTO> selectCharacterList(long novelNum, long uuid) throws NoPermissionException;
    List<CharacterDTO> searchCharacterList(long novelNum, String character);
    void updateCharacter(CharacterDTO characterDTO);
    void deleteCharacter(CharacterDTO characterDTO, long uuid) throws NoPermissionException;
    int deleteCharacterList(long novelNum, long uuid) throws NoPermissionException;

    void addCharacterData(CharacterRequestDataDTO characterRequestDataDTO, long uuid) throws NoPermissionException;
    CharacterResponseDataDTO selectCharacterData(EpisodeCharacterDTO episodeCharacterDTO, long uuid) throws NoPermissionException;

    //CharacterStatMapper 관련
    public List<CharacterDTO> selectCharactersByEpisode(long episodeNum, long uuid) throws NoPermissionException;
    public void insertEpisodeCharacter(EpisodeCharacterDTO episodeCharacterDTO, long uuid) throws NoPermissionException;
    public void deleteEpisodeCharacter(EpisodeCharacterDTO episodeCharacterDTO, long uuid) throws NoPermissionException;
    public List<StatResponseDTO> selectCharacterStatsByIds(EpisodeCharacterDTO episodeCharacterDTO);
    public void insertCharacterStat(CharacterStatDTO characterStatDTO, long uuid) throws NoPermissionException;
    public void updateCharacterStat(CharacterStatDTO characterStatDTO, long uuid) throws NoPermissionException;
    public void deleteCharacterStat(CharacterStatDTO characterStatDTO, long uuid) throws NoPermissionException;

    //CharacterEquipMapper 관련
    public CharacterEquipDTO selectCharacterEquipByIds(EpisodeCharacterDTO episodeCharacterDTO, long equipmentNum, long uuid) throws NoPermissionException; // 필요 없어 보임 그냥 Equipment에서 장비 정보 받아오는게 맞음
    public List<CharacterEquipDTO> selectCharacterEquipsByIds(EpisodeCharacterDTO episodeCharacterDTO, long uuid) throws NoPermissionException; // 아마 이걸로 장비번호를 받아서 Equipment에서 받아오는 식으로 작동
    public void insertCharacterEquip(CharacterEquipDTO characterEquipDTO, long uuid) throws NoPermissionException;
    public void deleteCharacterEquip(CharacterEquipDTO characterEquipDTO, long uuid) throws NoPermissionException;
}

