package com.example.hotelmanagment.controller;

import com.example.hotelmanagment.dto.OrderDto;
import com.example.hotelmanagment.dto.ResponseDto;
import com.example.hotelmanagment.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Tag(name = "order-controller", description = "Buyurtmalar uchun API lar")
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Buyurtma yaratish")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto<OrderDto>> createOrder(@RequestBody OrderDto orderDto) {
        log.trace("Accessing Order /order/create" + orderDto);
        return ResponseEntity.ok(orderService.save(orderDto));

    }

    @Operation(summary = "Buyurtmani yangilash")
    @PostMapping("/update/{id}")
    public ResponseEntity<ResponseDto<OrderDto>> updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        log.trace("Accessing Order /order/update/{}", id);
        return ResponseEntity.ok(orderService.update(id, orderDto));
    }

    @Operation(summary = "Id orqali buyurtmani olish")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ResponseDto<OrderDto>> getOrderById(@PathVariable Long id) {
        log.trace("Accessing Order /order/get/{}", id);
        return ResponseEntity.ok(orderService.getById(id));
    }

    @Operation(summary = "Barcha buyurtmani olish")
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllOrders(Pageable pageable) {
        log.trace("Accessing GetAll /order/getAll");
        Page<OrderDto> page = orderService.getAll(pageable);
        log.trace("GetAll /order/getAll {}", page.getSize());
        return ResponseEntity.ok(page.getContent());
    }

    @Operation(summary = "Buyurtmani tugatish")
    @PostMapping("checkout/{id}")
    public ResponseEntity<ResponseDto<OrderDto>> checkOut(@PathVariable Long id,
                                                          @RequestParam(value = "description") String description,
                                                          @RequestParam(value = "rating") Double rating){
        log.trace("Accessing Checkout /order/checkout/{}", id);
        return ResponseEntity.ok(orderService.checkOut(id, description, rating));
    }


}
