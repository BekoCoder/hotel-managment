package com.example.hotelmanagment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Mehmonxona bo'yicha ma'lumotlar")
public class HotelDto {
    @Schema(description = "Nomi")
    private String name;
    @Schema(description = "Manzili")
    private String address;
    @Schema(description = "telefon raqami")
    private String phone;
    @Schema(description = "email pochtasi")
    private String email;
    @Schema(description = "shahar")
    private String city;
}
