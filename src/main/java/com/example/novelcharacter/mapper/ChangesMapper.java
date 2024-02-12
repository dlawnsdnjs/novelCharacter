package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.ChangesDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChangesMapper {
    public void insertChanges(ChangesDTO changesDTO);
    public ChangesDTO selectChanges(long changeCode);
    public List<ChangesDTO> selectChangesList(String changeName);
    public void updateChanges(ChangesDTO changesDTO);
    public int deleteChanges(long changeCode);
}
