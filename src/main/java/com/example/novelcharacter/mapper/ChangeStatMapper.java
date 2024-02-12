package com.example.novelcharacter.mapper;

import com.example.novelcharacter.dto.ChangeStatDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChangeStatMapper {
    public void insertChangeStat(ChangeStatDTO changeStatDTO);
    public ChangeStatDTO selectChangeStat(long changeCode, long statCode);
    public List<ChangeStatDTO> selectChangeStatList(long changeCode);
    public void updateChangeStat(ChangeStatDTO changeStatDTO);
    public int deleteChangeStat(long changeCode);
}
