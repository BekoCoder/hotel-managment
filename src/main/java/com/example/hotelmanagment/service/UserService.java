package com.example.hotelmanagment.service;

import com.example.hotelmanagment.dto.JwtRequestDto;
import com.example.hotelmanagment.dto.JwtResponseDto;
import com.example.hotelmanagment.dto.ResponseDto;
import com.example.hotelmanagment.dto.UserDto;

import java.util.List;

public interface UserService {
    ResponseDto<UserDto> save(UserDto userDto);

    ResponseDto<JwtResponseDto> login(JwtRequestDto requestDto);

    ResponseDto<UserDto> update(UserDto userDto, Long userId);

    List<UserDto> getAllUsers();

    void deleteById(Long id);

    ResponseDto<UserDto> getUserById(Long id);


}
