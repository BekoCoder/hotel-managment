package com.example.hotelmanagment.service.impl;

import com.example.hotelmanagment.dto.JwtRequestDto;
import com.example.hotelmanagment.dto.JwtResponseDto;
import com.example.hotelmanagment.dto.UserDto;
import com.example.hotelmanagment.entity.User;
import com.example.hotelmanagment.enumeration.UserRoles;
import com.example.hotelmanagment.exceptions.CustomException;
import com.example.hotelmanagment.jwt.JwtService;
import com.example.hotelmanagment.repository.UserRepository;
import com.example.hotelmanagment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public UserDto save(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        user.setIsDeleted(0);
        user.setRoles(List.of(UserRoles.USER));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if (checkEmail(user.getEmail())) {
            throw new CustomException("Bunday foydalanuvchi oldin ro'yhatdan o'tgan!!! ");
        } else if (!checkPassword(userDto.getPassword())) {
            throw new CustomException("Parol uzunligi 5 va 16 uzunlik orasida bo'lishi kerak");
        }
        userRepository.save(user);
        return mapper.map(user, UserDto.class);
    }

    @Override
    public JwtResponseDto login(JwtRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(() -> new CustomException("Foydalanuvchi topilmadi!!!"));
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new CustomException("Parol yoki email noto'g'ri kiritildi!!!");
        }
        if (user.getIsDeleted() == 1) {
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
    public UserDto update(UserDto userDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException("Foydalanuvchi topilmadi!!!"));
        if (user.getIsDeleted() == 1) {
            throw new CustomException("Foydalanuvchi topilmadi!!!");
        }
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return mapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findByIsDeleted(0);
        if (users.isEmpty()) {
            throw new CustomException("Ma'lumot topilmadi!!!");
        }
        return users.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());

    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("Foydalanuvchi topilmadi!!!"));
        if (user.getIsDeleted() == 1) {
            throw new CustomException("Foydalanuvchi o'chirilgan!!!");
        }
        user.setIsDeleted(1);
        userRepository.save(user);
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            return mapper.map(byId.get(), UserDto.class);
        }
        throw new CustomException("Foydalanuvchi topilmadi!!!");
    }


    private boolean checkPassword(String password) {
        return password.length() >= 5 && password.length() <= 16;
    }

    private boolean checkEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
