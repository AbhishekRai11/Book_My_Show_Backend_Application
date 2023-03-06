package com.example.Book_My_Show.Entities;

import com.example.Book_My_Show.Enums.ShowType;
import jakarta.persistence.*;
//import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shows")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    LocalDate showDate;

    LocalTime showTime;

    @Enumerated(value = EnumType.STRING)
    private ShowType showType;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    //this is child wrt movie
    @ManyToOne
    @JoinColumn
    private MovieEntity movieEntity;

    //this is child wrt theater
    @ManyToOne
    @JoinColumn
    private TheaterEntity theaterEntity;

    //this is parent wrt to ticket
    @OneToMany(mappedBy = "showEntity",cascade = CascadeType.ALL)
    private List<TicketEntity> ticketEntityList =new ArrayList<>();

    //this is parent wrt to showSeatEntity
    @OneToMany(mappedBy = "showEntity",cascade = CascadeType.ALL)
    private List<ShowSeatEntity> showSeatEntityList=new ArrayList<>();


}
