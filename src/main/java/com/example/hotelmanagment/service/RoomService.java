package com.example.hotelmanagment.service;

import com.example.hotelmanagment.dto.ResponseDto;
import com.example.hotelmanagment.dto.RoomDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomService {

    ResponseDto<RoomDto> save(RoomDto roomDto);

    ResponseDto<RoomDto> getById(Long id);

    ResponseDto<RoomDto> update(RoomDto roomDto, Long id);

    ResponseDto<String> delete(Long id);

    ResponseDto<RoomDto> addRoomToHotel(Long hotelId, Long roomId);

    Page<RoomDto> getAllRooms(Pageable pageable);


}
