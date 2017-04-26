package com.gang.api.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Junwoo on 2017-04-07.
 */
@Getter
@Setter
@Builder
public class ResponseDto {
    private Dto dto;
    private Status status;
    private String message;

    enum Status{
        SUCCESS,
        FAILURE
    }

    public static ResponseDto ofSuccess(Dto data,String message){
        return ResponseDto.builder()
                .dto(data)
                .status(Status.SUCCESS)
                .message(message).build();
    }

    public static ResponseDto ofFailure(Dto data,String message){
        return ResponseDto.builder()
                .dto(data)
                .status(Status.FAILURE)
                .message(message).build();
    }
}
