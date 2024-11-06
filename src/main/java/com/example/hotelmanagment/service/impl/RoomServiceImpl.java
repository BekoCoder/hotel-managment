package com.example.hotelmanagment.service.impl;

import com.example.hotelmanagment.dto.RoomDto;
import com.example.hotelmanagment.entity.Hotel;
import com.example.hotelmanagment.entity.Room;
import com.example.hotelmanagment.enumeration.RoomType;
import com.example.hotelmanagment.exceptions.CustomException;
import com.example.hotelmanagment.exceptions.RoomNotFoundException;
import com.example.hotelmanagment.repository.HotelRepository;
import com.example.hotelmanagment.repository.RoomRepository;
import com.example.hotelmanagment.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
//    private final RoomMapper roomMapper;
    private final ModelMapper roomMapper;

    @Override
    public RoomDto save(RoomDto roomDto) {
        Room room = roomMapper.map(roomDto, Room.class);
        if (isExistRoom(roomDto.getRoomNumber())) {
            throw new RoomNotFoundException("Bunday raqamli xona mavjud!!! ");
        }
        if (roomDto.getRoomType().equals(RoomType.LUX)) {
            room.setType(RoomType.valueOf(roomDto.getRoomType()));
        } else if (roomDto.getRoomType().equals(RoomType.COMFORT)) {
            room.setType(RoomType.valueOf(roomDto.getRoomType()));
        } else if (roomDto.getRoomType().equals( RoomType.STANDART)) {
            room.setType(RoomType.valueOf(roomDto.getRoomType()));
        } else {
            throw new CustomException("Bunday turdagi xonalar mavjud emas!!! ");
        }
            roomRepository.save(room);
        return roomMapper.map(room, RoomDto.class);
    }

    @Override
    public RoomDto getById(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new CustomException("Bunday xona topilmadi!!!"));
        if (Objects.isNull(room)) {
            throw new RoomNotFoundException("Bunday xona topilmadi!!! ");
        }
        return roomMapper.map(room, RoomDto.class);
    }

    @Override
    public RoomDto update(RoomDto roomDto, Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new CustomException("Bunday xona topilmadi!!!"));
        if (Objects.isNull(room)) {
            throw new RoomNotFoundException("Bunday xona topilmadi!!! ");
        }
        room.setRoomNumber(roomDto.getRoomNumber());
        room.setPrice(roomDto.getPrice());
        return roomMapper.map(roomRepository.save(room), RoomDto.class);

    }

    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public RoomDto addRoomToHotel(Long hotelId, Long roomId) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        Optional<Room> room = roomRepository.findById(roomId);
        if (hotel.isPresent() && room.isPresent()) {
            room.get().setHotel(hotel.get());
            return roomMapper.map(roomRepository.save(room.get()), RoomDto.class);
        }
        throw new CustomException("Bunday xona topilmadi!!! ");
    }

    @Override
    public Page<RoomDto> getAllRooms(Pageable pageable) {
        return roomRepository.findAll(pageable).map((element) -> roomMapper.map(element, RoomDto.class));
    }

    private boolean isExistRoom(Integer number) {
        return roomRepository.findByRoomNumber(number).isPresent();
    }


}
