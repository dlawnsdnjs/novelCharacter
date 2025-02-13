package com.example.novelcharacter.mapper;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermanentItemEffectMapper {
    public PermanentItemEffectDTO selectPermanentItemEffectByIds(long itemNum, long statNum, String statType);
    public List<PermanentItemEffectDTO> selectPermanentItemEffectsByIds(long itemNum);
    public void insertPermanentItemEffect(PermanentItemEffectDTO permanentItemEffectDTO);
    public void updatePermanentItemEffect(PermanentItemEffectDTO permanentItemEffectDTO);
    public void deletePermanentItemEffect(PermanentItemEffectDTO permanentItemEffectDTO);
}
