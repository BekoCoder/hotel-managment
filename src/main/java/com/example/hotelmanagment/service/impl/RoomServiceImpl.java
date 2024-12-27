package com.example.hotelmanagment.service.impl;

import com.example.hotelmanagment.dto.ResponseDto;
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
    private final ModelMapper roomMapper;

    @Override
    public ResponseDto<RoomDto> save(RoomDto roomDto) {
        ResponseDto<RoomDto> responseDto = new ResponseDto<>();
        Room room = roomMapper.map(roomDto, Room.class);
        if (isExistRoom(room.getRoomNumber())) {
            throw new RoomNotFoundException("Bunday raqamli xona mavjud!!! ");
        }
        if (Objects.equals(roomDto.getType(), RoomType.LUX)) {
            room.setType(RoomType.valueOf(String.valueOf(roomDto.getType())));
        } else if (Objects.equals(roomDto.getType(), RoomType.COMFORT)) {
            room.setType(RoomType.valueOf(String.valueOf(roomDto.getType())));
        } else if (Objects.equals(roomDto.getType(), RoomType.STANDART)) {
            room.setType(RoomType.valueOf(String.valueOf(roomDto.getType())));
        } else {
            throw new CustomException("Bunday turdagi xonalar mavjud emas!!! ");
        }
        responseDto.setSuccess(true);
        responseDto.setMessage("Xona saqlandi");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(roomMapper.map(roomRepository.save(room), RoomDto.class));
        return responseDto;
    }

    @Override
    public ResponseDto<RoomDto> getById(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new CustomException("Bunday xona topilmadi!!!"));
        if (Objects.isNull(room)) {
            throw new RoomNotFoundException("Bunday xona topilmadi!!! ");
        }
        ResponseDto<RoomDto> responseDto = new ResponseDto<>();
        responseDto.setSuccess(true);
        responseDto.setMessage("Xona topildi");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(roomMapper.map(room, RoomDto.class));
        return responseDto;
    }

    @Override
    public ResponseDto<RoomDto> update(RoomDto roomDto, Long id) {
        ResponseDto<RoomDto> responseDto = new ResponseDto<>();
        Room room = roomRepository.findById(id).orElseThrow(() -> new CustomException("Bunday xona topilmadi!!!"));
        if (Objects.isNull(room)) {
            throw new RoomNotFoundException("Bunday xona topilmadi!!! ");
        }
        room.setRoomNumber(roomDto.getRoomNumber());
        responseDto.setSuccess(true);
        responseDto.setMessage("Xona o'zgartirildi");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(roomMapper.map(roomRepository.save(room), RoomDto.class));
        return responseDto;
    }

    @Override
    public void delete(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new CustomException("Bunday xona topilmadi!!!"));
        if (Objects.isNull(room)) {
            throw new RoomNotFoundException("Bunday xona topilmadi!!! ");
        }
        room.setIsDeleted(1);
        roomRepository.save(room);
    }

    @Override
    public ResponseDto<RoomDto> addRoomToHotel(Long hotelId, Long roomId) {
        ResponseDto<RoomDto> responseDto = new ResponseDto<>();
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        Optional<Room> room = roomRepository.findById(roomId);
        if (hotel.isPresent() && room.isPresent()) {
            Hotel hotel1 = hotel.get();
            Room room1 = room.get();
            if (room1.getIsDeleted() == 0 && hotel1.getIsDeleted() == 0) {
                room.get().setHotel(hotel.get());
                responseDto.setSuccess(true);
                responseDto.setMessage("Xona mehmonxonaga qo'shildi");
                responseDto.setRecordsTotal(1L);
                responseDto.setData(roomMapper.map(roomRepository.save(room.get()), RoomDto.class));
                return responseDto;
            }
        }
        throw new CustomException("Bunday xona topilmadi!!! ");
    }

    @Override
    public Page<RoomDto> getAllRooms(Pageable pageable) {
        return roomRepository.findAllByIsDeleted(pageable, 0).map((element) -> roomMapper.map(element, RoomDto.class));
    }

    private boolean isExistRoom(Integer number) {
        return roomRepository.findByRoomNumber(number).isPresent();
    }


}
