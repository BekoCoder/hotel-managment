package com.example.hotelmanagment.controller;

import com.example.hotelmanagment.dto.ResponseDto;
import com.example.hotelmanagment.dto.RoomDto;
import com.example.hotelmanagment.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
@Tag(name = "room-controller", description = "Xonalar uchun API lar")
@Slf4j
public class RoomController {
    private final RoomService roomService;

    @Operation(summary = "Xona yaratish")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto<RoomDto>> createRoom(@RequestBody RoomDto roomDto) {
        log.trace("Accessing Room /room/create" + roomDto);
        return ResponseEntity.ok(roomService.save(roomDto));
    }

    @Operation(summary = "Xonalarni yangilash")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto<RoomDto>> updateRoom(@RequestBody RoomDto roomDto, @PathVariable Long id) {
        log.trace("Accessing Room /room/update/{}", id);
        return ResponseEntity.ok(roomService.update(roomDto, id));
    }

    @Operation(summary = "Id orqali xonani olish")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ResponseDto<RoomDto>> getRoomById(@PathVariable Long id) {
        log.trace("Accessing Room /room/get/{}", id);
        return ResponseEntity.ok(roomService.getById(id));
    }

    @Operation(summary = "Barcha xonani olish")
    @GetMapping("/get-all")
    public ResponseEntity<List<RoomDto>> getAllRooms(Pageable pageable) {
        log.trace("Accessing GetAll /hotel/getAll");
        Page<RoomDto> page = roomService.getAllRooms(pageable);
        log.trace("GetAll /hotel/getAll {}", page.getSize());
        return ResponseEntity.ok(page.getContent());
    }

    @Operation(summary = "id orqali o'chirish")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto<String>> deleteRoom(@PathVariable Long id) {
        log.trace("Accessing Room /room/delete/{}", id);
        return ResponseEntity.ok(roomService.delete(id));
    }

    @Operation(summary = "xonalarni mehmonxonaga biriktirish")
    @PutMapping("/add-room-to-hotel/{hotelId}/{roomId}")
    public ResponseEntity<ResponseDto<RoomDto>> addRoomToHotel(@PathVariable Long hotelId, @PathVariable Long roomId) {
        return ResponseEntity.ok(roomService.addRoomToHotel(hotelId, roomId));
    }


}
