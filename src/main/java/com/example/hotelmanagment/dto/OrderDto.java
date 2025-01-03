package com.example.hotelmanagment.dto;

import com.example.hotelmanagment.enumeration.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "buyurtmalar bo'yicha ma'lumotlar")
public class OrderDto {

    @Schema(description = "buyurtma raqami")
    private Long id;
    private UserBasicDto user;
    private RoomBasicDto room;
    @Schema(description = "buyurtma sanasi")
    private LocalDate startDate;
    @Schema(description = "buyurtma tugash sanasi")
    private LocalDate endDate;
    @Schema(description = "buyurtma narxi")
    private Integer totalCost;
    @Schema(description = "buyurtma holati")
    private OrderStatus status;


}
