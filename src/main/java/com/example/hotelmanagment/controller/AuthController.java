package com.example.hotelmanagment.controller;

import com.example.hotelmanagment.dto.JwtRequestDto;
import com.example.hotelmanagment.dto.UserDto;
import com.example.hotelmanagment.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "user-controller", description = "Userlar uchun API lar")
public class AuthController {
    private final UserService userService;

    @Operation(summary = "Ro'yhatdan o'tish")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        userService.save(userDto);
        return new ResponseEntity<>("Muvaffaqiyatli ro'yhatdan o'tdingiz", HttpStatus.CREATED);
    }

    @Operation(summary = "Login")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequestDto jwtRequestDto) {
        userService.login(jwtRequestDto);
        return new ResponseEntity<>("Muvaffaqiyatli login", HttpStatus.OK);
    }


}
