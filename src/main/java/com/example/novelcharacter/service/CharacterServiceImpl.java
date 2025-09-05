package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.*;
import com.example.novelcharacter.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterMapper characterMapper;
    private final EpisodeCharacterMapper episodeCharacterMapper;
    private final CharacterStatMapper characterStatMapper;
    private final CharacterEquipMapper characterEquipMapper;
//    private final UserService userService;
    private final NovelService novelService;
    private final EpisodeService episodeService;
    private final StatMapper statMapper;


    @Autowired
    public CharacterServiceImpl(CharacterMapper characterMapper, EpisodeCharacterMapper episodeCharacterMapper ,CharacterStatMapper characterStatMapper, CharacterEquipMapper characterEquipMapper , UserService userService, NovelService novelService, EpisodeService episodeService, StatMapper statMapper){
        this.characterMapper = characterMapper;
        this.episodeCharacterMapper = episodeCharacterMapper;
        this.characterStatMapper = characterStatMapper;
        this.characterEquipMapper = characterEquipMapper;
//        this.userService = userService;
        this.novelService = novelService;
        this.episodeService = episodeService;
        this.statMapper = statMapper;
    }
    @Override
    public void insertCharacter(CharacterDTO characterDTO, long uuid) throws NoPermissionException {
        novelService.checkOwner(characterDTO.getNovelNum(), uuid);
        characterMapper.insertCharacter(characterDTO);
    }

    @Override
    public CharacterDTO selectCharacter(long characterNum) {
        return characterMapper.selectCharacter(characterNum);
    }

    @Override
    public void checkCharacterOwner(long uuid, long characterNum) throws NoPermissionException {
        if(characterMapper.checkCharacterOwner(uuid, characterNum) == 1){
        }
        else{
            throw new NoPermissionException("사용자가 작성한 등장인물이 아닙니다.");
        }
    }

//    @Override
//    public List<CharacterDTO> selectAllCharacters(long uuid) {
//        UserDTO userDTO = userService.getUserByUuid(uuid);
//        List<NovelWithFavoriteDTO> novels = novelService.selectAllNovel(userDTO.getUuid());
//
//        List<CharacterDTO> characters = new ArrayList<>();
//        for(NovelWithFavoriteDTO n : novels){
//            characters.addAll(selectCharacterList(n.getNovelNum()));
//        }
//        return characters;
//    }

    @Override
    public List<CharacterDTO> selectCharacterList(long novelNum, long uuid) throws NoPermissionException {
        novelService.checkOwner(novelNum, uuid);
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
    public void deleteCharacter(CharacterDTO characterDTO, long uuid) throws NoPermissionException {
        novelService.checkOwner(characterDTO.getNovelNum(), uuid);
        characterMapper.deleteCharacter(characterDTO.getCharacterNum());
    }

    @Override
    public int deleteCharacterList(long novelNum, long uuid) throws NoPermissionException {
        // 나중에 소설자체를 지울 때 하위 목록 지우는 용도에 사용할 예정
        novelService.checkOwner(novelNum, uuid);
        return characterMapper.deleteCharacterList(novelNum);
    }

    @Override
    public void addCharacterData(CharacterRequestDataDTO characterRequestDataDTO, long uuid) throws NoPermissionException {
        novelService.checkOwner(characterRequestDataDTO.getNovelNum(), uuid);
        long characterNum = characterRequestDataDTO.getCharacterNum();

        EpisodeCharacterDTO episodeCharacterDTO = new EpisodeCharacterDTO();
        episodeCharacterDTO.setCharacterNum(characterNum);
        episodeCharacterDTO.setEpisodeNum(characterRequestDataDTO.getEpisodeNum());
        episodeCharacterMapper.insertEpisodeCharacter(episodeCharacterDTO);

        List<String> statNames = new ArrayList<>();
        for(StatResponseDTO statResponseDTO : characterRequestDataDTO.getStats()){
            statNames.add(statResponseDTO.getStatName());
        }

        List<StatDTO> statDTOS = statMapper.selectStatList(statNames);


        for(long characterEquip: characterRequestDataDTO.getEquipments()){
            CharacterEquipDTO characterEquipDTO = new CharacterEquipDTO();
            characterEquipDTO.setCharacterNum(characterNum);
            characterEquipDTO.setEquipmentNum(characterEquip);
            characterEquipMapper.insertCharacterEquip(characterEquipDTO);
        }
    }

    @Override
    public CharacterResponseDataDTO selectCharacterData(EpisodeCharacterDTO episodeCharacterDTO, long uuid) throws NoPermissionException {
        episodeService.checkEpisodeOwner(episodeCharacterDTO.getEpisodeNum(), uuid);
        checkCharacterOwner(episodeCharacterDTO.getCharacterNum(), uuid);

        CharacterResponseDataDTO characterResponseDataDTO = new CharacterResponseDataDTO();
        List<StatResponseDTO> characterStatDTOS = selectCharacterStatsByIds(episodeCharacterDTO);
        characterResponseDataDTO.setCharacter(selectCharacter(episodeCharacterDTO.getCharacterNum()));
        List<CharacterEquipDTO> characterEquipDTOS = selectCharacterEquipsByIds(episodeCharacterDTO, uuid);

        List<EquipmentDataDTO> equipmentDataDTOS = new ArrayList<>();


        return null;
    }

    //아래는 CharacterStat 관련
    @Override
    public List<CharacterDTO> selectCharactersByEpisode(long episodeNum, long uuid) throws NoPermissionException {
        episodeService.checkEpisodeOwner(episodeNum, uuid);

        return episodeCharacterMapper.selectCharactersByEpisode(episodeNum);
    }

    @Override
    public void insertEpisodeCharacter(EpisodeCharacterDTO episodeCharacterDTO, long uuid) throws NoPermissionException {
        episodeService.checkEpisodeOwner(episodeCharacterDTO.getEpisodeNum(), uuid);
        checkCharacterOwner(episodeCharacterDTO.getCharacterNum(), uuid);
        episodeCharacterMapper.insertEpisodeCharacter(episodeCharacterDTO);
    }

    @Override
    public void deleteEpisodeCharacter(EpisodeCharacterDTO episodeCharacterDTO, long uuid) throws NoPermissionException {
        episodeService.checkEpisodeOwner(episodeCharacterDTO.getEpisodeNum(), uuid);
        checkCharacterOwner(episodeCharacterDTO.getCharacterNum(), uuid);

        episodeCharacterMapper.deleteEpisodeCharacterByEpisode(episodeCharacterDTO);
    }

    @Override
    public List<StatResponseDTO> selectCharacterStatsByIds(EpisodeCharacterDTO episodeCharacterDTO){
        return characterStatMapper.selectCharacterStatsResponse(episodeCharacterDTO);
    }


    @Override
    public void insertCharacterStat(CharacterStatDTO characterStatDTO, long uuid) throws NoPermissionException {
        checkCharacterOwner(uuid, characterStatDTO.getCharacterNum());
        characterStatMapper.insertCharacterStat(characterStatDTO);
    }

    @Override
    public void updateCharacterStat(CharacterStatDTO characterStatDTO, long uuid) throws NoPermissionException {
        checkCharacterOwner(uuid, characterStatDTO.getCharacterNum());

        characterStatMapper.updateCharacterStat(characterStatDTO);
    }

    @Override
    public void deleteCharacterStat(CharacterStatDTO characterStatDTO, long uuid) throws NoPermissionException {
        checkCharacterOwner(uuid, characterStatDTO.getCharacterNum());

        characterStatMapper.deleteCharacterStat(characterStatDTO);
    }


    // 아래는 CharacterEquip관련
    @Override
    public CharacterEquipDTO selectCharacterEquipByIds(EpisodeCharacterDTO episodeCharacterDTO, long equipmentNum, long uuid) throws NoPermissionException {
        episodeService.checkEpisodeOwner(episodeCharacterDTO.getEpisodeNum(), uuid);
        checkCharacterOwner(episodeCharacterDTO.getCharacterNum(), uuid);
        return characterEquipMapper.selectCharacterEquipByIds(episodeCharacterDTO.getEpisodeNum(), episodeCharacterDTO.getCharacterNum(), equipmentNum);
    }

    @Override
    public List<CharacterEquipDTO> selectCharacterEquipsByIds(EpisodeCharacterDTO episodeCharacterDTO, long uuid) throws NoPermissionException {
        episodeService.checkEpisodeOwner(episodeCharacterDTO.getEpisodeNum(), uuid);
        checkCharacterOwner(episodeCharacterDTO.getCharacterNum(), uuid);
        return characterEquipMapper.selectCharacterEquipsByIds(episodeCharacterDTO.getEpisodeNum(), episodeCharacterDTO.getCharacterNum());
    }

    @Override
    public void insertCharacterEquip(CharacterEquipDTO characterEquipDTO, long uuid) throws NoPermissionException {
        checkCharacterOwner(uuid ,characterEquipDTO.getCharacterNum());
        characterEquipMapper.insertCharacterEquip(characterEquipDTO);
    }

    @Override
    public void deleteCharacterEquip(CharacterEquipDTO characterEquipDTO, long uuid) throws NoPermissionException {
        checkCharacterOwner(uuid ,characterEquipDTO.getCharacterNum());
        characterEquipMapper.deleteCharacterEquip(characterEquipDTO);
    }
}
