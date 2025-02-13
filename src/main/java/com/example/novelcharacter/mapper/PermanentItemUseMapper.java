package com.example.novelcharacter.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermanentItemUseMapper {
    public PermanentItemUseDTO selectPermanentItemUseByIds(long novelNum, long episodeNum, long characterNum, long itemNum);
    public List<PermanentItemUseDTO> selectPermanentItemUsesByIds(long novelNum, long episodeNum, long characterNum);
    public void insertPermanentItemUse(PermanentItemUseDTO permanentItemUseDTO);
    public void deletePermanentItemUse(PermanentItemUseDTO permanentItemUseDTO);
}
