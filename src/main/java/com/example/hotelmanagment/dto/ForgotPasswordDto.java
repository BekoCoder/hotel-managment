package com.example.hotelmanagment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Parolni tiklash")
public class ForgotPasswordDto {
    @Schema(description = "OTP parol")
    private int otpCode;
    @Schema(description = "email")
    private String email;
    @Schema(description = "yangi parol")
    private String newPassword;

}
