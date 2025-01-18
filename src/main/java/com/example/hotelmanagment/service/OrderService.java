package com.example.hotelmanagment.service;

import com.example.hotelmanagment.dto.OrderDto;
import com.example.hotelmanagment.dto.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    ResponseDto<OrderDto> save(OrderDto orderDto);

    ResponseDto<OrderDto> getById(Long id);

    ResponseDto<OrderDto> update(Long id, OrderDto orderDto);

    ResponseDto<String> delete(Long id);

    Page<OrderDto> getAll(Pageable pageable);

    ResponseDto<OrderDto> checkOut(Long orderId, String description, Double rating);

    ResponseDto<OrderDto> cancel(Long orderId);
}
