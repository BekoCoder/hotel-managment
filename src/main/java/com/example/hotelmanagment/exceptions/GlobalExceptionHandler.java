package com.example.hotelmanagment.exceptions;

import com.example.hotelmanagment.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException e) {
        ResponseDto<Object> responseDto = new ResponseDto<>();
        responseDto.setSuccess(false);
        responseDto.setReason(e.getMessage());
        return ResponseEntity.badRequest().body(responseDto);
    }

    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<Object> handleHotelNotFoundException(HotelNotFoundException e) {
        String message = e.getMessage();
        ResponseDto<Object> responseDto = new ResponseDto<>();
        responseDto.setSuccess(false);
        responseDto.setReason(message);
        return ResponseEntity.badRequest().body(responseDto);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        ResponseDto<Object> responseDto = new ResponseDto<>();
        responseDto.setSuccess(false);
        responseDto.setReason(e.getMessage());
        return ResponseEntity.badRequest().body(responseDto);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<Object> handleRoomNotFoundException(RoomNotFoundException e) {
        ResponseDto<Object> responseDto = new ResponseDto<>();
        responseDto.setSuccess(false);
        responseDto.setReason(e.getMessage());
        return ResponseEntity.badRequest().body(responseDto);
    }

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<Object> handlePaymentException(PaymentException e) {
        ResponseDto<Object> responseDto = new ResponseDto<>();
        responseDto.setSuccess(false);
        responseDto.setReason(e.getMessage());
        return ResponseEntity.badRequest().body(responseDto);
    }
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException e) {
        ResponseDto<Object> responseDto = new ResponseDto<>();
        responseDto.setSuccess(false);
        responseDto.setReason(e.getMessage());
        return ResponseEntity.badRequest().body(responseDto);
    }
}
