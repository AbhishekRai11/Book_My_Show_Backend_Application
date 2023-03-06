package com.example.Book_My_Show.Convertor;

import com.example.Book_My_Show.Entities.ShowEntity;
import com.example.Book_My_Show.EntryDto.ShowEntryDto;

public class ShowConvertor {

    public static ShowEntity convertEntryDtoToEntity(ShowEntryDto showEntryDto){
        ShowEntity showEntity=ShowEntity.builder()
                .showDate(showEntryDto.getLocalDate())
                .showTime(showEntryDto.getLocalTime())
                .showType(showEntryDto.getShowType())
                .build();

        return showEntity;
    }
}
