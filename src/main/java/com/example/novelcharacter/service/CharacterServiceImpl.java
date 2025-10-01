package com.example.novelcharacter.service;

import com.example.novelcharacter.component.StatCalculator;
import com.example.novelcharacter.dto.*;
import com.example.novelcharacter.mapper.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterMapper characterMapper;
    private final EpisodeCharacterMapper episodeCharacterMapper;
    private final CharacterStatMapper characterStatMapper;
    private final CharacterEquipMapper characterEquipMapper;
//    private final UserService userService;
    private final NovelService novelService;
    private final EpisodeService episodeService;
    private final EquipmentService equipmentService;
    private final StatService statService;
    private final StatCalculator statCalculator;


    @Autowired
    public CharacterServiceImpl(CharacterMapper characterMapper, EpisodeCharacterMapper episodeCharacterMapper ,CharacterStatMapper characterStatMapper
            , CharacterEquipMapper characterEquipMapper , NovelService novelService, EpisodeService episodeService, EquipmentService equipmentService
            , StatService statService, StatCalculator statCalculator){
        this.characterMapper = characterMapper;
        this.episodeCharacterMapper = episodeCharacterMapper;
        this.characterStatMapper = characterStatMapper;
        this.characterEquipMapper = characterEquipMapper;
        this.novelService = novelService;
        this.episodeService = episodeService;
        this.equipmentService = equipmentService;
        this.statService = statService;
        this.statCalculator = statCalculator;
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
        if(characterMapper.checkCharacterOwner(uuid, characterNum) != 1){
            throw new NoPermissionException("사용자가 작성한 등장인물이 아닙니다.");
        }
    }

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

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void addCharacterData(CharacterRequestDataDTO characterRequestDataDTO, long uuid) throws NoPermissionException {
        novelService.checkOwner(characterRequestDataDTO.getNovelNum(), uuid);
        long episodeNum = characterRequestDataDTO.getEpisodeNum();
        long characterNum = characterRequestDataDTO.getCharacterNum();

        deleteEpisodeCharacter(new EpisodeCharacterDTO(episodeNum, characterNum));
        insertEpisodeCharacter(episodeNum, characterNum);
        insertCharacterStatList(episodeNum, characterNum, characterRequestDataDTO.getStats());
        insertCharacterEquipList(episodeNum, characterNum, characterRequestDataDTO.getEquipments());
    }

    @Override
    public CharacterResponseDataDTO selectCharacterData(EpisodeCharacterDTO episodeCharacterDTO, long uuid) throws NoPermissionException {
        episodeService.checkEpisodeOwner(episodeCharacterDTO.getEpisodeNum(), uuid);
        checkCharacterOwner(uuid, episodeCharacterDTO.getCharacterNum());

        CharacterResponseDataDTO characterResponseDataDTO = new CharacterResponseDataDTO();
        characterResponseDataDTO.setCharacter(selectCharacter(episodeCharacterDTO.getCharacterNum()));
        List<StatInfoDTO> characterStatDTOS = selectCharacterStatsByIds(episodeCharacterDTO);
        characterResponseDataDTO.setStats(characterStatDTOS);
        List<CharacterEquipDTO> characterEquipDTOS = selectCharacterEquipsByIds(episodeCharacterDTO);

        List<Long> equipList = characterEquipDTOS
                .stream()
                .map(CharacterEquipDTO::getEquipmentNum)
                .toList();


        List<EquipmentDataDTO> equipmentDataDTOS = equipmentService.selectEquipmentDataList(equipList);
        characterResponseDataDTO.setEquipment(equipmentDataDTOS);
        List<StatInfoDTO> finalStats = statCalculator.calculate(characterStatDTOS, equipmentDataDTOS);
        characterResponseDataDTO.setFinalStats(finalStats);

        System.out.println("character Data: "+ characterResponseDataDTO);

        return characterResponseDataDTO;
    }

    //아래는 CharacterStat 관련
    @Override
    public List<CharacterDTO> selectCharactersByEpisode(long episodeNum, long uuid) throws NoPermissionException {
        episodeService.checkEpisodeOwner(episodeNum, uuid);

        return episodeCharacterMapper.selectCharactersByEpisode(episodeNum);
    }

    @Override
    public void insertEpisodeCharacter(long episodeNum, long characterNum){
        EpisodeCharacterDTO episodeCharacterDTO = new EpisodeCharacterDTO();
        episodeCharacterDTO.setEpisodeNum(episodeNum);
        episodeCharacterDTO.setCharacterNum(characterNum);
        episodeCharacterMapper.insertEpisodeCharacter(episodeCharacterDTO);
    }

    @Override
    public void deleteEpisodeCharacter(EpisodeCharacterDTO episodeCharacterDTO, long uuid) throws NoPermissionException {
        episodeService.checkEpisodeOwner(episodeCharacterDTO.getEpisodeNum(), uuid);
        checkCharacterOwner(episodeCharacterDTO.getCharacterNum(), uuid);

        episodeCharacterMapper.deleteEpisodeCharacter(episodeCharacterDTO);
    }

    @Override
    public void deleteEpisodeCharacter(EpisodeCharacterDTO episodeCharacterDTO){
        episodeCharacterMapper.deleteEpisodeCharacter(episodeCharacterDTO);
    }

    @Override
    public List<StatInfoDTO> selectCharacterStatsByIds(EpisodeCharacterDTO episodeCharacterDTO){
        return characterStatMapper.selectCharacterStatsResponse(episodeCharacterDTO);
    }


    @Override
    public void insertCharacterStat(CharacterStatDTO characterStatDTO, long uuid) throws NoPermissionException {
        checkCharacterOwner(uuid, characterStatDTO.getCharacterNum());
        characterStatMapper.insertCharacterStat(characterStatDTO);
    }

    @Override
    public void insertCharacterStatList(long episodeNum, long characterNum, List<StatInfoDTO> statInfoDTOS){
        List<String> statNames = statInfoDTOS
                .stream()
                .map(StatInfoDTO::getStatName)
                .collect(Collectors.toList());

        List<StatDTO> statDTOS = statService.selectStatList(statNames);
        Map<String, Long> statCodeMap = statDTOS.stream()
                .collect(Collectors.toMap(StatDTO::getStatName, StatDTO::getStatCode));

        List<StatRequestDTO> statRequests = statInfoDTOS.stream()
                .map(resp -> {
                    Long statCode = statCodeMap.get(resp.getStatName());
//                    if (statCode == null) {
//                        throw new IllegalArgumentException("존재하지 않는 스탯: " + resp.getStatName());
//                    }

                    StatRequestDTO dto = new StatRequestDTO();
                    dto.setStatCode(statCode);
                    dto.setValue(resp.getValue());
                    return dto;
                })
                .toList();

        characterStatMapper.insertCharacterStatList(episodeNum, characterNum, statRequests);
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


    // 아래는 CharacterEquip 관련
    @Override
    public CharacterEquipDTO selectCharacterEquipByIds(EpisodeCharacterDTO episodeCharacterDTO, long equipmentNum, long uuid) throws NoPermissionException {
        episodeService.checkEpisodeOwner(episodeCharacterDTO.getEpisodeNum(), uuid);
        checkCharacterOwner(episodeCharacterDTO.getCharacterNum(), uuid);
        return characterEquipMapper.selectCharacterEquipByIds(episodeCharacterDTO.getEpisodeNum(), episodeCharacterDTO.getCharacterNum(), equipmentNum);
    }

    @Override
    public List<CharacterEquipDTO> selectCharacterEquipsByIds(EpisodeCharacterDTO episodeCharacterDTO) {
        return characterEquipMapper.selectCharacterEquipsByIds(episodeCharacterDTO.getEpisodeNum(), episodeCharacterDTO.getCharacterNum());
    }

    @Override
    public void insertCharacterEquip(CharacterEquipDTO characterEquipDTO, long uuid) throws NoPermissionException {
        checkCharacterOwner(uuid ,characterEquipDTO.getCharacterNum());
        characterEquipMapper.insertCharacterEquip(characterEquipDTO);
    }

    @Override
    public void insertCharacterEquipList(long episodeNum, long characterNum, List<Long> equipments){
        System.out.println("equipments:"+equipments);
        characterEquipMapper.insertCharacterEquipList(episodeNum, characterNum, equipments);
    }

    @Override
    public void deleteCharacterEquip(CharacterEquipDTO characterEquipDTO, long uuid) throws NoPermissionException {
        checkCharacterOwner(uuid ,characterEquipDTO.getCharacterNum());
        characterEquipMapper.deleteCharacterEquip(characterEquipDTO);
    }
}
