package com.example.hotelmanagment.service.impl;

import com.example.hotelmanagment.dto.PaymentDto;
import com.example.hotelmanagment.dto.ResponseDto;
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
    public ResponseDto<PaymentDto> createPayment(PaymentDto paymentDto) {
        Payment payment = mapper.map(paymentDto, Payment.class);
        ResponseDto<PaymentDto> responseDto = new ResponseDto<>();
        responseDto.setSuccess(true);
        responseDto.setMessage("To'lov saqlandi");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(mapper.map(paymentRepository.save(payment), PaymentDto.class));
        return responseDto;
    }

    @Override
    public ResponseDto<PaymentDto> updatePayment(PaymentDto paymentDto, Long id) {
        ResponseDto<PaymentDto> responseDto = new ResponseDto<>();
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("To'lov topilmadi"));
        if (Objects.isNull(payment) || payment.getIsDeleted() == 1) {
            throw new RuntimeException("To'lov topilmadi");
        }
        payment.setAmount(paymentDto.getAmount());
        payment.setPaymentDate(paymentDto.getPaymentDate());
        responseDto.setSuccess(true);
        responseDto.setMessage("To'lov o'zgartirildi");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(mapper.map(paymentRepository.save(payment), PaymentDto.class));
        return responseDto;
    }

    @Override
    public ResponseDto<String> deleteById(Long id) {
        ResponseDto<String> responseDto = new ResponseDto<>();
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("To'lov topilmadi"));
        if (Objects.isNull(payment) || payment.getIsDeleted() == 1) {
            throw new RuntimeException("To'lov topilmadi");
        }
        payment.setIsDeleted(1);
        responseDto.setSuccess(true);
        responseDto.setMessage("To'lov o'chirildi");
        responseDto.setRecordsTotal(1L);
        paymentRepository.save(payment);
        return responseDto;
    }

    @Override
    public ResponseDto<PaymentDto> getPaymentById(Long id) {
        ResponseDto<PaymentDto> responseDto = new ResponseDto<>();
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("To'lov topilmadi"));
        if (Objects.isNull(payment) || payment.getIsDeleted() == 1) {
            throw new RuntimeException("To'lov topilmadi");
        }
        responseDto.setSuccess(true);
        responseDto.setMessage("To'lov topildi");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(mapper.map(payment, PaymentDto.class));
        return responseDto;
    }

    @Override
    public Page<PaymentDto> getAllPayments(Pageable pageable) {
        return paymentRepository.findAllByIsDeleted(0, pageable).map(payment -> mapper.map(payment, PaymentDto.class));
    }


}
