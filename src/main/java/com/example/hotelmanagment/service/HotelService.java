package com.example.hotelmanagment.service;

import com.example.hotelmanagment.dto.HotelDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelService {
    HotelDto save(HotelDto hotelDto);

    Page<HotelDto> getAll(Pageable pageable);

    HotelDto getById(Long id);

    void deleteById(Long id);

    HotelDto updateById(Long id, HotelDto hotelDto);

}
