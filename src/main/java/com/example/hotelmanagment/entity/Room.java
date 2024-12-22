package com.example.hotelmanagment.entity;

import com.example.hotelmanagment.enumeration.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room")
public class Room extends AbstractEntity {
    private Integer roomNumber;
    private Boolean isActive = true;
    private Double price;
    private String description;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    @JoinColumn(name = "hotel_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Hotel hotel;


    @OneToMany(fetch = FetchType.LAZY)
    private List<Order> order = new ArrayList<>();


}
