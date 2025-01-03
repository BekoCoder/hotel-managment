package com.example.hotelmanagment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Foydalanuvchi bo'yicha qisqa ma'lumotlar")
public class UserBasicDto {
    @Schema(description = "Foydalanuvchi Idsi")
    private Long id;

    @Schema(description = "Foydalanuvchi ismi")
    private String firstName;

    @Schema(description = "Foydalanuvchi familiyasi")
    private String lastName;

    @Schema(description = "Foydalanuvchi emaili")
    private String email;
}
