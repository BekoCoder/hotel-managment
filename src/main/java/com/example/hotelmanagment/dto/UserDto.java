package com.example.hotelmanagment.dto;

import com.example.hotelmanagment.enumeration.UserRoles;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@Schema(description = "User bo'yicha ma'lumotlar")
public class UserDto {
    @Schema(description = "Id")
    private Long id;

    @Schema(description = "Foydalanuvchi ismi")
    private String firstName;

    @Schema(description = "Foydalanuvchi familiyasi")
    private String lastName;

    @Schema(description = "Foydalanuvchi emaili")
    private String email;

    @Schema(description = "Foydalanuvchi paroli")
    private String password;

    @Schema(description = "Role")
    private List<UserRoles> roles;


}
