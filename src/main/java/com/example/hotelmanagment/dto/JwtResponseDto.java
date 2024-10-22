package com.example.hotelmanagment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "Jwt Responce")
@Builder
public class JwtResponseDto {
    private String token;
    private String email;
    private String password;
}
