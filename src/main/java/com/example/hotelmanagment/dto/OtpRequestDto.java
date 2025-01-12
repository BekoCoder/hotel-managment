package com.example.hotelmanagment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "otp yuborish uchun email")
public class OtpRequestDto {
    @Schema(description = "email")
    private String email;
}
