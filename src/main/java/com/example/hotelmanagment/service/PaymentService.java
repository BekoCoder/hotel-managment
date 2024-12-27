package com.example.hotelmanagment.service;

import com.example.hotelmanagment.dto.PaymentDto;
import com.example.hotelmanagment.dto.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {
    ResponseDto<PaymentDto> createPayment(PaymentDto paymentDto);

    ResponseDto<PaymentDto> updatePayment(PaymentDto paymentDto, Long id);

    void deleteById(Long id);

    ResponseDto<PaymentDto> getPaymentById(Long id);

    Page<PaymentDto> getAllPayments(Pageable pageable);
}
