package com.example.hotelmanagment.service.impl;

import com.example.hotelmanagment.dto.*;
import com.example.hotelmanagment.entity.User;
import com.example.hotelmanagment.enumeration.UserRoles;
import com.example.hotelmanagment.exceptions.CustomException;
import com.example.hotelmanagment.jwt.JwtService;
import com.example.hotelmanagment.repository.UserRepository;
import com.example.hotelmanagment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Value("${spring.mail.username}")
    private String fromAccount;

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final JavaMailSender javaMailSender;


    @Override
    public ResponseDto<UserDto> save(UserDto userDto) {
        ResponseDto<UserDto> responseDto = new ResponseDto<>();
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
        responseDto.setSuccess(true);
        responseDto.setMessage("Foydalanuvchi saqlandi");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(mapper.map(user, UserDto.class));
        return responseDto;
    }

    @Override
    public ResponseDto<JwtResponseDto> login(JwtRequestDto requestDto) {
        ResponseDto<JwtResponseDto> responseDto = new ResponseDto<>();
        User user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(() -> new CustomException("Foydalanuvchi topilmadi!!!"));
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new CustomException("Parol yoki email noto'g'ri kiritildi!!!");
        }
        if (user.getIsDeleted() == 1) {
            throw new CustomException("Foydalanuvchi topilmadi!!!");
        }
        String token = jwtService.generateToken(user);
        responseDto.setSuccess(true);
        responseDto.setMessage("Token saqlandi");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(JwtResponseDto.builder()
                .token(token)
                .email(user.getEmail())
                .password(user.getPassword())
                .build());
        return responseDto;
    }

    @Override
    public ResponseDto<UserDto> update(UserDto userDto, Long userId) {
        ResponseDto<UserDto> responseDto = new ResponseDto<>();
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException("Foydalanuvchi topilmadi!!!"));
        if (user.getIsDeleted() == 1) {
            throw new CustomException("Foydalanuvchi topilmadi!!!");
        }
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        responseDto.setSuccess(true);
        responseDto.setMessage("Foydalanuvchi o'zgartirildi");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(mapper.map(user, UserDto.class));
        return responseDto;
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
    public ResponseDto<UserDto> getUserById(Long id) {
        ResponseDto<UserDto> responseDto = new ResponseDto<>();
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            responseDto.setSuccess(true);
            responseDto.setMessage("Foydalanuvchi topildi");
            responseDto.setRecordsTotal(1L);
            responseDto.setData(mapper.map(byId.get(), UserDto.class));
            return responseDto;
        }
        throw new CustomException("Foydalanuvchi topilmadi!!!");
    }

    @Override
    public ResponseDto<UserDto> forgotPassword(ForgotPasswordDto forgotPasswordDto) {
        ResponseDto<UserDto> responseDto = new ResponseDto<>();
        User user = userRepository.findByEmail(forgotPasswordDto.getEmail()).orElseThrow(() -> new CustomException("Foydalanuvchi topilmadi!!!"));
        if (Objects.isNull(user) || user.getIsDeleted() == 1) {
            throw new CustomException("Foydalanuvchi topilmadi!!!");
        }
        if (forgotPasswordDto.getOtpCode() == user.getOtpCode()) {
            user.setPassword(passwordEncoder.encode(forgotPasswordDto.getNewPassword()));
            userRepository.save(user);
            responseDto.setSuccess(true);
            responseDto.setMessage("Parol o'zgartirildi");
            responseDto.setRecordsTotal(1L);
            responseDto.setData(mapper.map(user, UserDto.class));
            return responseDto;
        }
        throw new CustomException("Kod noto'g'ri kiritildi!!!");


    }

    @Override
    public ResponseDto<String> sendOtp(OtpRequestDto dto) {
        SimpleMailMessage message = new SimpleMailMessage();
        ResponseDto<String> responseDto = new ResponseDto<>();
        Random random = new Random();
        int lower = (int) Math.pow(10, 3);
        int upper = (int) Math.pow(10, 4);
        int code = random.nextInt(upper - lower) + lower;
        System.out.println(code);
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new CustomException("Foydalanuvchi topilmadi!!!"));
        if (user.getIsDeleted() == 1) {
            throw new CustomException("Foydalanuvchi topilmadi!!!");
        }
        message.setFrom(fromAccount);
        message.setTo(dto.getEmail());
        message.setSubject("Parolni tiklash");
        message.setText("OTP parolingiz: " + code);
        javaMailSender.send(message);
        user.setOtpCode(code);
        userRepository.save(user);

        responseDto.setSuccess(true);
        responseDto.setMessage("Kod yuborildi");
        responseDto.setRecordsTotal(1L);
        responseDto.setData("Kod: " + code);
        return responseDto;
    }

    private boolean checkPassword(String password) {
        return password.length() >= 5 && password.length() <= 16;
    }

    private boolean checkEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
