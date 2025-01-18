package com.example.hotelmanagment.service;

import com.example.hotelmanagment.dto.HotelDto;
import com.example.hotelmanagment.dto.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelService {
    ResponseDto<HotelDto> save(HotelDto hotelDto);

    Page<HotelDto> getAll(Pageable pageable);

    ResponseDto<HotelDto> getById(Long id);

    ResponseDto<String > deleteById(Long id);

    ResponseDto<HotelDto> updateById(Long id, HotelDto hotelDto);

}
