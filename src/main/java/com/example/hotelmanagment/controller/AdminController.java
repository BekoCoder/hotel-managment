package com.example.hotelmanagment.controller;

import com.example.hotelmanagment.dto.ResponseDto;
import com.example.hotelmanagment.dto.UserDto;
import com.example.hotelmanagment.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "admin-controller", description = "Admin Panel uchun API lar")
@Slf4j
public class AdminController {
    private final UserService userService;


    @Operation(summary = "Userlarni id orqali yangilash")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto<UserDto>> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        log.trace("Accessing UPDATE /admin/update/{}", id);
        return ResponseEntity.ok(userService.update(userDto, id));
    }


    @Operation(summary = "id orqali o'chirish")
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        log.trace("Accessing DELETE /admin/delete-by-id/{}", id);
        userService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Operation(summary = "Id orqali userni olish")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ResponseDto<UserDto>> getById(@PathVariable Long id) {
        log.trace("Accessing GET /admin/get-by-id/{}", id);
        ResponseDto<UserDto> userById = userService.getUserById(id);
        log.trace("Returned to fron-end: {} ", userById);
        return ResponseEntity.ok(userById);
    }


    @Operation(summary = "Barcha userlarni olish")
    @GetMapping("/get-all")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
