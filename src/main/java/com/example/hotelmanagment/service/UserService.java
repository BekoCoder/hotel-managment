package com.example.hotelmanagment.service;

import com.example.hotelmanagment.dto.JwtRequestDto;
import com.example.hotelmanagment.dto.JwtResponseDto;
import com.example.hotelmanagment.dto.UserDto;

import java.util.List;

public interface UserService {
    JwtResponseDto save(UserDto userDto);

    JwtResponseDto login(JwtRequestDto requestDto);

    UserDto update(UserDto userDto);

    List<UserDto> getAllUsers();

    void deleteById(Long id);

    UserDto getUserById(Long id);


}
