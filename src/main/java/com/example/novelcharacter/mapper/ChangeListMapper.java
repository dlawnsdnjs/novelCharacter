package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.ChangeListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChangeListMapper {
    public void insertChangeList(ChangeListDTO changeListDTO);
    public List<ChangeListDTO> selectChangeList(long novelNum, String characterName, long time);
    public int deleteChangeList(ChangeListDTO changeListDTO);
}
