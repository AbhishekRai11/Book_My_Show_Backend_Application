package com.example.Book_My_Show.Entities;

import com.example.Book_My_Show.Enums.SeatType;
import jakarta.persistence.*;
//import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "show_seats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isBooked;

    private String seatNo;

    private int price;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private Date bookedAt;


    @ManyToOne
    @JoinColumn
    private ShowEntity showEntity;
}
