package com.example.hotelmanagment.service;

import com.example.hotelmanagment.dto.*;

import java.util.List;

public interface UserService {
    ResponseDto<UserDto> save(UserDto userDto);

    ResponseDto<JwtResponseDto> login(JwtRequestDto requestDto);

    ResponseDto<UserDto> update(UserDto userDto, Long userId);

    List<UserDto> getAllUsers();

    void deleteById(Long id);

    ResponseDto<UserDto> getUserById(Long id);

    ResponseDto<UserDto> forgotPassword(ForgotPasswordDto forgotPasswordDto);


    ResponseDto<String> sendOtp(String email);

}
