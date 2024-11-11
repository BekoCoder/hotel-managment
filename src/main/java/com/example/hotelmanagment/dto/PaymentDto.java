package com.example.hotelmanagment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "to'lovlar bo'yicha ma'lumotlar")
public class PaymentDto {
    @Schema(description = "To'lov miqdori")
    private Double amount;

    @Schema(description = "To'langan vaqt")
    private LocalDateTime paymentDate;
}
