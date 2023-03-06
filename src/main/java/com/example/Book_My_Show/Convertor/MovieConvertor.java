package com.example.Book_My_Show.Convertor;

import com.example.Book_My_Show.Entities.MovieEntity;
import com.example.Book_My_Show.EntryDto.MovieEntryDto;

public class MovieConvertor {
    public static MovieEntity entryDtoToEntity(MovieEntryDto movieEntryDto){
        MovieEntity movieEntity=MovieEntity.builder()
                .movieName(movieEntryDto.getMovieName())
                .duration(movieEntryDto.getDuration())
                .rating(movieEntryDto.getRating())
                .language(movieEntryDto.getLanguage())
                .genre(movieEntryDto.getGenre())
                .build();

        return movieEntity;
    }
}
