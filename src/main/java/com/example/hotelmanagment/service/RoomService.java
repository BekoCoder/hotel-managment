package com.example.hotelmanagment.service;

import com.example.hotelmanagment.dto.RoomDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomService {

    RoomDto save(RoomDto roomDto);

    RoomDto getById(Long id);

    RoomDto update(RoomDto roomDto, Long id);

    void delete(Long id);

    RoomDto addRoomToHotel(Long hotelId, Long roomId);

    Page<RoomDto> getAllRooms(Pageable pageable);


}
