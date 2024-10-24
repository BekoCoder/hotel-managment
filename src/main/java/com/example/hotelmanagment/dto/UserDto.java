package com.example.hotelmanagment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "User bo'yicha ma'lumotlar")
public class UserDto {

    @Schema(description = "Firstname")
    private String firstName;

    @Schema(description = "Lastname")
    private String lastName;

    @Schema(description = "Email")
    private String email;

    @Schema(description = "Password")
    private String password;


}
