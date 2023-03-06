package com.example.Book_My_Show.EntryDto;

import com.example.Book_My_Show.Enums.Genre;
import com.example.Book_My_Show.Enums.Language;
import lombok.Data;

@Data
public class MovieEntryDto {
    private String movieName;

    private double rating;

    private int duration;

    private Language language;

    private Genre genre;
}
