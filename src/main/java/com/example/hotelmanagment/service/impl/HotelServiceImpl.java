package com.example.hotelmanagment.service.impl;

import com.example.hotelmanagment.dto.HotelDto;
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

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final ModelMapper mapper;

    @Override
    public HotelDto save(HotelDto hotelDto) {
        Hotel hotel = mapper.map(hotelDto, Hotel.class);
        if (isExistHotel(hotel.getName())) {
            throw new CustomException("Bunday mehmonxona mavjud");
        }
        return mapper.map(hotelRepository.save(hotel), HotelDto.class);

    }

    @Override
    public Page<HotelDto> getAll(Pageable pageable) {
        return hotelRepository.findAll(pageable).map(hotel -> mapper.map(hotel, HotelDto.class));
    }

    @Override
    public HotelDto getById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException("Mehmonxona topilmadi!!!"));
        if (hotel == null) {
            throw new HotelNotFoundException("Mehmonxona topilmadi!!!");
        }
        return mapper.map(hotel, HotelDto.class);
    }

    @Override
    public void deleteById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException("Mehmonxona topilmadi!!!"));
        hotelRepository.delete(hotel);
    }

    @Override
    public HotelDto updateById(Long id, HotelDto hotelDto) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException("Mehmonxona topilmadi!!!"));
        if (hotel == null) {
            throw new HotelNotFoundException("Mehmonxona topilmadi!!!");
        }
        if (isExistHotel(hotelDto.getName())) {
            throw new CustomException("Bunday mehmonxona mavjud");
        }
        hotel.setName(hotelDto.getName());
        hotel.setPhone(hotelDto.getPhone());
        hotel.setEmail(hotelDto.getEmail());
        return mapper.map(hotelRepository.save(hotel), HotelDto.class);
    }

    private boolean isExistHotel(String name) {
        return hotelRepository.findByName(name).isPresent();
    }
}
