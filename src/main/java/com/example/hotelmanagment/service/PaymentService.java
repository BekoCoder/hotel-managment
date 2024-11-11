package com.example.hotelmanagment.service;

import com.example.hotelmanagment.dto.PaymentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {
    PaymentDto createPayment(PaymentDto paymentDto);

    PaymentDto updatePayment(PaymentDto paymentDto, Long id);

    void deleteById(Long id);

    PaymentDto getPaymentById(Long id);

    Page<PaymentDto> getAllPayments(Pageable pageable);
}
