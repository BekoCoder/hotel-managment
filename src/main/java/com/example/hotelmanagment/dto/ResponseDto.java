package com.example.hotelmanagment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Getter
@Setter
public class ResponseDto<T> {
    private boolean success;
    private String reason;
    private long recordsTotal = 0L;
    private T data;
    private String message;

    public ResponseDto() {
    }

    public ResponseDto(boolean success, String reason) {
        this.success = success;
        this.reason = reason;
    }

    public ResponseDto(boolean success, String reason, T data) {
        this.success = success;
        this.reason = reason;
        this.data = data;
    }

    public static <T> ResponseDto<T> ok(T data) {
        ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.setSuccess(true);
        responseDto.setReason(null);
        responseDto.setData(data);
        return responseDto;
    }

    public static <T> ResponseDto<T> error(String reason, T data) {
        ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.setSuccess(false);
        responseDto.setReason(reason);
        responseDto.setData(data);
        return responseDto;
    }
}