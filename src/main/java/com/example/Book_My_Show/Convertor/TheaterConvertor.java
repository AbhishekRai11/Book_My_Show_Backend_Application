package com.example.Book_My_Show.Convertor;

import com.example.Book_My_Show.Entities.TheaterEntity;
import com.example.Book_My_Show.EntryDto.TheaterEntryDto;

public class TheaterConvertor {

    public static TheaterEntity entryDtoToEntity(TheaterEntryDto theaterEntryDto){
        return TheaterEntity.builder()
                .location(theaterEntryDto.getLocation())
                .name(theaterEntryDto.getName())
                .build();


    }
}
