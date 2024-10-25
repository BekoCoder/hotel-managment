package com.example.hotelmanagment.service;

import com.example.hotelmanagment.dto.RoomDto;

public interface RoomService {

    RoomDto save(RoomDto roomDto);

    RoomDto getById(Long id);

    RoomDto update(RoomDto roomDto, Long id);

    void delete(Long id);

    RoomDto addRoomToHotel(Long hotelId, RoomDto roomDto);


}
