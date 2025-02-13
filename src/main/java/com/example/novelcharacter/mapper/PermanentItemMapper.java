package com.example.novelcharacter.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermanentItemMapper {
    public PermanentItemDTO selectPermanentItemById(long itemNum);
    public List<PermanentItemDTO> selectPermanentItemsById(long novelNum);
    public void insertPermanentItem(PermanentItemDTO permanentItemDTO);
    public void updatePermanentItem(PermanentItemDTO permanentItemDTO);
    public void deletePermanentItem(PermanentItemDTO permanentItemDTO);
}
