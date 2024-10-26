package com.example.hotelmanagment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Xona bo'yicha ma'lumotlar")
public class RoomDto {
    @Schema(description = "xona raqami")
    private Integer roomNumber;
    @Schema(description = "narxi")
    private Double price;
    @Schema(description = "ta'rifi")
    private String description;

    @Schema(description = "xona turi")
    private String roomType;
}
