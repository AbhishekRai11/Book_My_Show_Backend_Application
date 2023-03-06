package com.example.Book_My_Show.EntryDto;

import com.example.Book_My_Show.Enums.ShowType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowEntryDto {
    private LocalTime localTime;
    private LocalDate localDate;
    private ShowType showType;
    private int movieId;
    private int theaterId;
    private int classicSeatPrice;
    private int premiumSeatPrice;

}
