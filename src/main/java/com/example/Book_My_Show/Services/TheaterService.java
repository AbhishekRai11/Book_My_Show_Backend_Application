package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Convertor.TheaterConvertor;
import com.example.Book_My_Show.Entities.MovieEntity;
import com.example.Book_My_Show.Entities.TheaterEntity;
import com.example.Book_My_Show.Entities.TheaterSeatEntity;
import com.example.Book_My_Show.EntryDto.TheaterEntryDto;
import com.example.Book_My_Show.Enums.SeatType;
import com.example.Book_My_Show.Repository.TheaterRepository;
import com.example.Book_My_Show.Repository.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    public String addTheater(TheaterEntryDto theaterEntryDto) throws Exception{
        if(theaterEntryDto.getName()==null || theaterEntryDto.getLocation()==null){
            throw new Exception("pls give valid name and location");
        }


        TheaterEntity theaterEntity = TheaterConvertor.entryDtoToEntity(theaterEntryDto);

        List<TheaterSeatEntity> theaterSeatEntityList=createTheaterSeats(theaterEntryDto,theaterEntity);

        theaterEntity.setTheaterSeatEntityList(theaterSeatEntityList);

        theaterRepository.save(theaterEntity);
        return "";
    }

    private List<TheaterSeatEntity> createTheaterSeats(TheaterEntryDto theaterEntryDto,TheaterEntity theaterEntity){
        int noClassicSeats=theaterEntryDto.getClassicSeatCount();
        int noPremiumSeats=theaterEntryDto.getPremiumSeatCout();

        List<TheaterSeatEntity> theaterSeatEntityList=new ArrayList<>();

        for(int count=1;count<noClassicSeats;count++){
            TheaterSeatEntity theaterSeatEntity=TheaterSeatEntity.builder()
                    .seatNo(count+"C")
                    .seatType(SeatType.CLASSIC)
                    .theaterEntity(theaterEntity)
                    .build();

            theaterSeatEntityList.add(theaterSeatEntity);
        }

        for(int count=1;count<noPremiumSeats;count++){
            TheaterSeatEntity theaterSeatEntity=TheaterSeatEntity.builder()
                    .seatNo(count+"P")
                    .seatType(SeatType.PREMIUM)
                    .theaterEntity(theaterEntity)
                    .build();

            theaterSeatEntityList.add(theaterSeatEntity);
        }
        //theaterSeatRepository.saveAll(theaterSeatEntityList);

        return theaterSeatEntityList;

    }
}
