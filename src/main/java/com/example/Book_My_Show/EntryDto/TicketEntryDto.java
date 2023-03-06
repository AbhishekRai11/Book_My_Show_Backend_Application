package com.example.Book_My_Show.EntryDto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TicketEntryDto {
    private int showId;
    private int UserId;
    private List<String> requestedSeat=new ArrayList<>();



}
