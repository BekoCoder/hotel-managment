package com.example.hotelmanagment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Xona bo'yicha qisqa ma'lumotlar")
public class RoomBasicDto {
    @Schema(description = "xona Idsi")
    private Long id;

    @Schema(description = "xona raqami")
    private Integer roomNumber;

    @Schema(description = "xona narxi")
    private Integer price;

    @Schema(description = "xona holati")
    private Boolean isActive;
}
