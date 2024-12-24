package com.example.hotelmanagment.dto;

import com.example.hotelmanagment.enumeration.RoomType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Schema(description = "Xona bo'yicha ma'lumotlar")
public class RoomDto {

    @Schema(description = "xona Idsi")
    private Long id;

    @Schema(description = "xona raqami")
    private Integer roomNumber;
    @Schema(description = "ta'rifi")
    private String description;

    @Schema(description = "xona narxi")
    private Integer price;

    @Schema(description = "xona holati")
    private Boolean isActive;

    @Schema(description = "xona turi")
    @Enumerated(EnumType.STRING)
    private RoomType type;

}
