
package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.ChangeTimeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChangeTimeMapper {
    public void insertChangeTime(ChangeTimeDTO changeTimeDTO);
    public ChangeTimeDTO selectChangeTime(long novelNum, String characterName, long time);
    public List<ChangeTimeDTO> selectChangeTimeList(long novelNum, String characterName);
    public void updateChangeTime(ChangeTimeDTO changeTimeDTO);
    public int deleteChangeTime(long novelNum, String characterName, long time);
}
