package com.example.hotelmanagment.service.impl;

import com.example.hotelmanagment.dto.OrderDto;
import com.example.hotelmanagment.entity.Order;
import com.example.hotelmanagment.entity.Room;
import com.example.hotelmanagment.entity.User;
import com.example.hotelmanagment.enumeration.OrderStatus;
import com.example.hotelmanagment.exceptions.OrderNotFoundException;
import com.example.hotelmanagment.repository.OrderRepository;
import com.example.hotelmanagment.repository.RoomRepository;
import com.example.hotelmanagment.repository.UserRepository;
import com.example.hotelmanagment.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper mapper;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Override
    public OrderDto save(OrderDto orderDto) {
        Order order = mapper.map(orderDto, Order.class);
        Room room = roomRepository.findById(orderDto.getRoom().getId()).orElseThrow(() -> new OrderNotFoundException("Bunday xona topilmadi !!!"));
        User user = userRepository.findById(orderDto.getUser().getId()).orElseThrow(() -> new OrderNotFoundException("Bunday foydalanuvchi topilmadi !!!"));
        if (Objects.isNull(room) || room.getIsDeleted() == 1) {
            throw new OrderNotFoundException("Bunday xona topilmadi !!!");
        }
        if(room.getIsActive().equals(false)){
            throw new OrderNotFoundException("Bunday xona band qilingan !!!");
        }
        else {
            order.setStartDate(orderDto.getStartDate());
            order.setEndDate(orderDto.getEndDate());
            order.setUser(user);
            order.setRoom(room);
            long days = ChronoUnit.DAYS.between(order.getStartDate(), order.getEndDate());
            order.setTotalCost((int) (room.getPrice() * days));
            room.setIsActive(false);
            order.setStatus(OrderStatus.CONFIRMED);
            roomRepository.save(room);
            return mapper.map(orderRepository.save(order), OrderDto.class);
        }

    }

    @Override
    public OrderDto getById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Buyurtma topilmadi !!!"));
        if (Objects.isNull(order) || order.getIsDeleted() == 1) {
            throw new OrderNotFoundException("Buyurtma topilmadi !!!");
        }
        return mapper.map(order, OrderDto.class);
    }

    @Override
    public OrderDto update(Long id, OrderDto orderDto) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Buyurtma topilmadi !!!"));
        if (Objects.isNull(order) || order.getIsDeleted() == 1) {
            throw new OrderNotFoundException("Buyurtma topilmadi !!!");
        }

        return mapper.map(orderRepository.save(order), OrderDto.class);
    }

    @Override
    public void delete(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Buyurtma topilmadi !!!"));
        order.setIsDeleted(1);
        orderRepository.save(order);
    }

    @Override
    public Page<OrderDto> getAll(Pageable pageable) {
        return orderRepository.findAllByIsDeleted(0, pageable).map(order -> mapper.map(order, OrderDto.class));
    }

}
