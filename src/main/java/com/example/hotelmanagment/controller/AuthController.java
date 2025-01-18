package com.example.hotelmanagment.controller;

import com.example.hotelmanagment.dto.JwtRequestDto;
import com.example.hotelmanagment.dto.JwtResponseDto;
import com.example.hotelmanagment.dto.ResponseDto;
import com.example.hotelmanagment.dto.UserDto;
import com.example.hotelmanagment.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "auth-controller", description = "Login va Register uchun API lar")
public class AuthController {
    private final UserService userService;

    @Operation(summary = "Ro'yhatdan o'tish")
    @PostMapping("/register")
    public ResponseEntity<ResponseDto<UserDto>> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @Operation(summary = "Login")
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<JwtResponseDto>> login(@RequestBody JwtRequestDto jwtRequestDto) {
        return ResponseEntity.ok(userService.login(jwtRequestDto));

    }


}
