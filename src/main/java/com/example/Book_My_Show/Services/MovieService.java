package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Convertor.MovieConvertor;
import com.example.Book_My_Show.Entities.MovieEntity;
import com.example.Book_My_Show.EntryDto.MovieEntryDto;
import com.example.Book_My_Show.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieEntryDto movieEntryDto) throws Exception{
        MovieEntity movieEntity= MovieConvertor.entryDtoToEntity(movieEntryDto);

        movieRepository.save(movieEntity);

        return "Movie Added Successfully";

    }
}
