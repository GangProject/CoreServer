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
    private Status status;
    private String message;

    enum Status{
        SUCCESS,
        FAILURE
    }

    public static ResponseDto ofSuccess(String message){
        return ResponseDto.builder()
                .status(Status.SUCCESS)
                .message(message).build();
    }

    public static ResponseDto ofFailure(String message){
        return ResponseDto.builder()
                .status(Status.FAILURE)
                .message(message).build();
    }
}
