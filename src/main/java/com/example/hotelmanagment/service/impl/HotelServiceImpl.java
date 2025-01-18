package com.example.hotelmanagment.service.impl;

import com.example.hotelmanagment.dto.HotelDto;
import com.example.hotelmanagment.dto.ResponseDto;
import com.example.hotelmanagment.entity.Hotel;
import com.example.hotelmanagment.exceptions.CustomException;
import com.example.hotelmanagment.exceptions.HotelNotFoundException;
import com.example.hotelmanagment.repository.HotelRepository;
import com.example.hotelmanagment.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final ModelMapper mapper;

    @Override
    public ResponseDto<HotelDto> save(HotelDto hotelDto) {
        ResponseDto<HotelDto> responseDto = new ResponseDto<>();
        Hotel hotel = mapper.map(hotelDto, Hotel.class);
        if (isExistHotel(hotel.getName())) {
            throw new HotelNotFoundException("Bunday mehmonxona mavjud");
        }
        responseDto.setSuccess(true);
        responseDto.setMessage("Mehmonxona saqlandi");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(mapper.map(hotelRepository.save(hotel), HotelDto.class));
        return responseDto;

    }

    @Override
    public Page<HotelDto> getAll(Pageable pageable) {
        return hotelRepository.findAllByIsDeleted(0, pageable).map(hotel -> mapper.map(hotel, HotelDto.class));
    }

    @Override
    public ResponseDto<HotelDto> getById(Long id) {
        ResponseDto<HotelDto> responseDto = new ResponseDto<>();
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException("Mehmonxona topilmadi!!!"));
        if (hotel == null || hotel.getIsDeleted() == 1) {
            throw new HotelNotFoundException("Mehmonxona topilmadi!!!");
        }
        responseDto.setSuccess(true);
        responseDto.setMessage("Mehmonxona topildi");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(mapper.map(hotel, HotelDto.class));
        return responseDto;

    }

    @Override
    public ResponseDto<String> deleteById(Long id) {
        ResponseDto<String> responseDto = new ResponseDto<>();
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException("Mehmonxona topilmadi!!!"));
        if (Objects.isNull(hotel)) {
            throw new HotelNotFoundException("Mehmonxona topilmadi!!!");
        }
        responseDto.setSuccess(true);
        responseDto.setMessage("Mehmonxona o'chirildi");
        responseDto.setRecordsTotal(1L);
        hotel.setIsDeleted(1);
        hotelRepository.save(hotel);
        return responseDto;
    }

    @Override
    public ResponseDto<HotelDto> updateById(Long id, HotelDto hotelDto) {
        ResponseDto<HotelDto> responseDto = new ResponseDto<>();
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException("Mehmonxona topilmadi!!!"));
        if (hotel == null || hotel.getIsDeleted() == 1) {
            throw new HotelNotFoundException("Mehmonxona topilmadi!!!");
        }
        if (isExistHotel(hotelDto.getName())) {
            throw new CustomException("Bunday mehmonxona mavjud");
        }
        hotel.setName(hotelDto.getName());
        hotel.setPhone(hotelDto.getPhone());
        hotel.setEmail(hotelDto.getEmail());
        responseDto.setSuccess(true);
        responseDto.setMessage("Mehmonxona o'zgartirildi");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(mapper.map(hotelRepository.save(hotel), HotelDto.class));
        return responseDto;
    }

    private boolean isExistHotel(String name) {
        return hotelRepository.findByName(name).isPresent();
    }
}
