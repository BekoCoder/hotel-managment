package com.example.hotelmanagment.controller;

import com.example.hotelmanagment.dto.PaymentDto;
import com.example.hotelmanagment.dto.ResponseDto;
import com.example.hotelmanagment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Tag(name = "payment-controller", description = "To'lovlar uchun API lar")
public class PaymentController {
    private final PaymentService paymentService;

    @Operation(summary = "Qo'shish")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto<PaymentDto>> createPayment(@RequestBody PaymentDto paymentDto) {
        return ResponseEntity.ok(paymentService.createPayment(paymentDto));
    }

    @Operation(summary = "Yangilash")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto<PaymentDto>> updatePayment(@RequestBody PaymentDto paymentDto, @PathVariable Long id) {
        return ResponseEntity.ok(paymentService.updatePayment(paymentDto, id));
    }

    @Operation(summary = "Id orqali olish")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ResponseDto<PaymentDto>> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @Operation(summary = "Hammasini olish")
    @GetMapping("/get-all")
    public ResponseEntity<List<PaymentDto>> getAllPayments(Pageable pageable) {
        Page<PaymentDto> page = paymentService.getAllPayments(pageable);
        return ResponseEntity.ok(page.getContent());
    }

    @Operation(summary = "Id orqali olish")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto<String>> deletePayment(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.deleteById(id));
    }

}
