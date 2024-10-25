package com.example.hotelmanagment.service.impl;

import com.example.hotelmanagment.dto.RoomDto;
import com.example.hotelmanagment.repository.HotelRepository;
import com.example.hotelmanagment.repository.RoomRepository;
import com.example.hotelmanagment.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final ModelMapper mapper;
    private final HotelRepository hotelRepository;

    @Override
    public RoomDto save(RoomDto roomDto) {
        return null;
    }

    @Override
    public RoomDto getById(Long id) {
        return null;
    }

    @Override
    public RoomDto update(RoomDto roomDto, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public RoomDto addRoomToHotel(Long hotelId, RoomDto roomDto) {
        return null;
    }


}
