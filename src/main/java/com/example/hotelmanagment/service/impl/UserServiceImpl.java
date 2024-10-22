package com.example.hotelmanagment.service.impl;

import com.example.hotelmanagment.dto.JwtRequestDto;
import com.example.hotelmanagment.dto.JwtResponseDto;
import com.example.hotelmanagment.dto.UserDto;
import com.example.hotelmanagment.entity.User;
import com.example.hotelmanagment.exceptions.CustomException;
import com.example.hotelmanagment.jwt.JwtService;
import com.example.hotelmanagment.repository.UserRepository;
import com.example.hotelmanagment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public JwtResponseDto save(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if (checkEmail(user.getEmail())) {
            throw new CustomException("Bunday foydalanuvchi oldin ro'yhatdan o'tgan!!! ");
        } else if (!checkPassword(userDto.getPassword())) {
            throw new CustomException("Parol uzunligi 5 va 16 uzunlik orasida bo'lishi kerak");
        }
        userRepository.save(user);
        return mapper.map(user, JwtResponseDto.class);
    }

    @Override
    public JwtResponseDto login(JwtRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(() -> new CustomException("Foydalanuvchi topilmadi!!!"));
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new CustomException("Parol yoki email noto'g'ri kiritildi!!!");
        }
        if(user.getIsDeleted()==1){
            throw new CustomException("Foydalanuvchi topilmadi!!!");
        }
        String token = jwtService.generateToken(user);
        return JwtResponseDto.builder()
                .token(token)
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    @Override
    public UserDto update(UserDto userDto) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public UserDto getUserById(Long id) {
        return null;
    }


    private boolean checkPassword(String password) {
        return password.length() >= 5 && password.length() <= 16;
    }

    private boolean checkEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
