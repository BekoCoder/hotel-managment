package com.example.hotelmanagment.service.impl;

import com.example.hotelmanagment.dto.PaymentDto;
import com.example.hotelmanagment.entity.Payment;
import com.example.hotelmanagment.repository.PaymentRepository;
import com.example.hotelmanagment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ModelMapper mapper;

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        Payment payment = mapper.map(paymentDto, Payment.class);
        return mapper.map(paymentRepository.save(payment), PaymentDto.class);
    }

    @Override
    public PaymentDto updatePayment(PaymentDto paymentDto, Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("To'lov topilmadi"));
        if (Objects.isNull(payment) || payment.getIsDeleted() == 1) {
            throw new RuntimeException("To'lov topilmadi");
        }
        payment.setAmount(paymentDto.getAmount());
        payment.setPaymentDate(paymentDto.getPaymentDate());
        return mapper.map(paymentRepository.save(payment), PaymentDto.class);
    }

    @Override
    public void deleteById(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("To'lov topilmadi"));
        if (Objects.isNull(payment) || payment.getIsDeleted() == 1) {
            throw new RuntimeException("To'lov topilmadi");
        }
        payment.setIsDeleted(1);
        paymentRepository.save(payment);
    }

    @Override
    public PaymentDto getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("To'lov topilmadi"));
        if (Objects.isNull(payment) || payment.getIsDeleted() == 1) {
            throw new RuntimeException("To'lov topilmadi");
        }
        return mapper.map(payment, PaymentDto.class);
    }

    @Override
    public Page<PaymentDto> getAllPayments(Pageable pageable) {
        return paymentRepository.findAllByIsDeleted(0, pageable).map(payment -> mapper.map(payment, PaymentDto.class));
    }


}
