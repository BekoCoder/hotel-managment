package com.example.hotelmanagment.service;

import com.example.hotelmanagment.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderDto save(OrderDto orderDto);

    OrderDto getById(Long id);

    OrderDto update(Long id, OrderDto orderDto);

    void delete(Long id);

    Page<OrderDto> getAll(Pageable pageable);


}
