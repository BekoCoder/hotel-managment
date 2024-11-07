package com.example.hotelmanagment.dto;

import com.example.hotelmanagment.enumeration.RoomType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Schema(description = "Xona bo'yicha ma'lumotlar")
public class RoomDto {
    @Schema(description = "xona raqami")
    private Integer roomNumber;
    @Schema(description = "narxi")
    private Double price;
    @Schema(description = "ta'rifi")
    private String description;

    @Schema(description = "xona turi")
    @Enumerated(EnumType.STRING)
    private RoomType type;

}
