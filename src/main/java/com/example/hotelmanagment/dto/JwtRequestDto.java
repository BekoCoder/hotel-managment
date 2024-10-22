package com.example.hotelmanagment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Jwt")
public class JwtRequestDto {

    private String email;
    private String password;
}
