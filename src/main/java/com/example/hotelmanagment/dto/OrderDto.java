package com.example.hotelmanagment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "buyurtmalar bo'yicha ma'lumotlar")
public class OrderDto {

    @Schema(description = "buyurtma berilgan sana")
    private LocalDateTime orderDate;
    @Schema(description = "xonaga kirgan sana")
    private LocalDateTime checkInDate;
    @Schema(description = "xonadan chiqqan sana")
    private LocalDateTime checkOutDate;
    @Schema(description = "xizmat sifati haqida")
    private String description;
    @Schema(description = "xonaga qo'yilgan baho")
    private Double appreciation;

}
