package com.example.hotelmanagment.controller;

import com.example.hotelmanagment.dto.HotelDto;
import com.example.hotelmanagment.dto.ResponseDto;
import com.example.hotelmanagment.service.HotelService;
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
@RequestMapping("/hotel")
@RequiredArgsConstructor
@Tag(name = "hotel-controller", description = "Mehmonxona bo'yicha API lar")
@Slf4j
public class HotelController {
    private final HotelService hotelService;

    @Operation(summary = "Mehmonxona yaratish")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto<HotelDto>> create(@RequestBody HotelDto hotelDto) {
        log.trace("Accessing Create /hotel/create/" + hotelDto);
        return ResponseEntity.ok(hotelService.save(hotelDto));
    }


    @Operation(summary = "Mehmonxonani yangilash")
    @PutMapping("/update/{id}")
    public ResponseEntity<HotelDto> update(@PathVariable("id") Long id, @RequestBody HotelDto hotelDto) {
        log.trace("Accessing Update /hotel/update/{}", id);
        return ResponseEntity.ok(hotelService.updateById(id, hotelDto));
    }

    @Operation(summary = "Id orqali olish")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ResponseDto<HotelDto>> getById(@PathVariable("id") Long id) {
        log.trace("Accessing Get by-id /hotel/get-by-id/{}", id);
        return ResponseEntity.ok(hotelService.getById(id));
    }

    @Operation(summary = "Barcha mehmonxonani olish")
    @GetMapping("/getAll")
    public ResponseEntity<List<HotelDto>> getAll(Pageable pageable) {
        log.trace("Accessing GetAll /hotel/getAll");
        Page<HotelDto> page = hotelService.getAll(pageable);
        log.trace("GetAll /hotel/getAll {}", page.getSize());
        return ResponseEntity.ok(page.getContent());
    }

    @Operation(summary = "Id orqali o'chirish")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        log.trace("Accessing Delete /hotel/delete/{}", id);
        hotelService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
