package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Convertor.TicketConverter;
import com.example.Book_My_Show.Entities.ShowEntity;
import com.example.Book_My_Show.Entities.ShowSeatEntity;
import com.example.Book_My_Show.Entities.TicketEntity;
import com.example.Book_My_Show.Entities.UserEntity;
import com.example.Book_My_Show.EntryDto.TicketEntryDto;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.TicketRepository;
import com.example.Book_My_Show.Repository.UserRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public String addTicket(TicketEntryDto ticketEntryDto) throws Exception{
        TicketEntity ticketEntity= TicketConverter.convertEntryToEntity(ticketEntryDto);

        boolean isvalid=checkValidityOfRequestedSeats(ticketEntryDto);

        if(isvalid==false){
            throw new Exception("Requested seat is already booked");
        }

        ShowEntity showEntity=showRepository.findById(ticketEntryDto.getShowId()).get();
        List<ShowSeatEntity> showSeatEntityList=showEntity.getShowSeatEntityList();
        List<String> requestedSeats=ticketEntryDto.getRequestedSeat();


        int totalAmt=0;
        for(ShowSeatEntity showSeatEntity: showSeatEntityList){
            String seatNo=showSeatEntity.getSeatNo();

            if(requestedSeats.contains(seatNo) ){
                totalAmt+=showSeatEntity.getPrice();
                showSeatEntity.setBooked(true);
                showSeatEntity.setBookedAt(new Date());
            }
        }

        ticketEntity.setTotalAmount(totalAmt);
        ticketEntity.setMovieName(showEntity.getMovieEntity().getMovieName());
        ticketEntity.setShowDate(showEntity.getShowDate());
        ticketEntity.setShowTime(showEntity.getShowTime());
        ticketEntity.setTheaterName(showEntity.getTheaterEntity().getName());

        String allotedSeat=getAllotedSeatFromShowSeat(requestedSeats);
        ticketEntity.setBookedSeat(allotedSeat);

        UserEntity userEntity=userRepository.findById(ticketEntryDto.getUserId()).get();
        ticketEntity.setUserEntity(userEntity);
        ticketEntity.setShowEntity(showEntity);

        List<TicketEntity> ticketEntityList=showEntity.getTicketEntityList();
        ticketEntityList.add(ticketEntity);
        showEntity.setTicketEntityList(ticketEntityList);

        showRepository.save(showEntity);

        List<TicketEntity> ticketEntityList1=userEntity.getTicketEntityList();
        ticketEntityList1.add(ticketEntity);
        userEntity.setTicketEntityList(ticketEntityList);

        userRepository.save(userEntity);

//Email part
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("abhishekrai911814@gmail.com");
        mimeMessageHelper.setTo(userEntity.getEmail());
        mimeMessageHelper.setText("seat booked "+ allotedSeat);
        mimeMessageHelper.setSubject("confirm book ticket");
        javaMailSender.send(mimeMessage);
        System.out.println("mail send successfully");



        return "Ticket has been successfully added";

    }

    public boolean checkValidityOfRequestedSeats(TicketEntryDto ticketEntryDto){
        int showId=ticketEntryDto.getShowId();
        List<String> requestedSeats=ticketEntryDto.getRequestedSeat();

        ShowEntity showEntity=showRepository.findById(showId).get();
        List<ShowSeatEntity> showSeatEntityList=showEntity.getShowSeatEntityList();

        for(ShowSeatEntity showSeatEntity: showSeatEntityList){
            String seatNo=showSeatEntity.getSeatNo();

            if(requestedSeats.contains(seatNo) ){
                if(showSeatEntity.isBooked()==true){
                    return false;
                }
            }
        }
        return true;

    }

    public String getAllotedSeatFromShowSeat(List<String > requestedSeats){
        String result="";

        for(String seat : requestedSeats){
            result = result+seat+" , ";
        }
        return result;
    }


}
