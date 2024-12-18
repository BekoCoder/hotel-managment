package com.example.hotelmanagment.service.impl;

import com.example.hotelmanagment.dto.OrderDto;
import com.example.hotelmanagment.entity.Order;
import com.example.hotelmanagment.exceptions.OrderNotFoundException;
import com.example.hotelmanagment.repository.OrderRepository;
import com.example.hotelmanagment.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;
    @Override
    public OrderDto save(OrderDto orderDto) {
        Order order = mapper.map(orderDto, Order.class);
        return mapper.map(orderRepository.save(order), OrderDto.class);
    }

    @Override
    public OrderDto getById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Buyurtma topilmadi !!!"));
        if(Objects.isNull(order)){
            throw  new OrderNotFoundException("Buyurtma topilmadi !!!");
        }
        return mapper.map(order, OrderDto.class);
    }

    @Override
    public OrderDto update(Long id, OrderDto orderDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Buyurtma topilmadi !!!"));
        order.setIsDeleted(1);
        orderRepository.save(order);
    }

    @Override
    public Page<OrderDto> getAll(Pageable pageable) {
        return orderRepository.findAll(pageable).map(order -> mapper.map(order, OrderDto.class));
    }

}
