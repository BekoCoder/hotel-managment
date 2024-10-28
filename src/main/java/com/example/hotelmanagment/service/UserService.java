package com.example.hotelmanagment.service;

import com.example.hotelmanagment.dto.JwtRequestDto;
import com.example.hotelmanagment.dto.JwtResponseDto;
import com.example.hotelmanagment.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto save(UserDto userDto);

    JwtResponseDto login(JwtRequestDto requestDto);

    UserDto update(UserDto userDto, Long userId);

    List<UserDto> getAllUsers();

    void deleteById(Long id);

    UserDto getUserById(Long id);


}
