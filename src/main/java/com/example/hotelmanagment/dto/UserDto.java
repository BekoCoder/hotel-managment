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

    @Schema(description = "Firstname")
    private String firstName;

    @Schema(description = "Lastname")
    private String lastName;

    @Schema(description = "Email")
    private String email;

    @Schema(description = "Password")
    private String password;

    @Schema(description = "Role")
    private List<UserRoles> roles;


}
