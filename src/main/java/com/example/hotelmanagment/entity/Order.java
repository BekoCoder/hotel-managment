package com.example.hotelmanagment.entity;

import com.example.hotelmanagment.enumeration.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer totalCost;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @JoinColumn(name = "room_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;


    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String description;

    private Double appreciation; // baholash


}
