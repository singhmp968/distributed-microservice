package com.cc.orderService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MyCustomBadRequestException extends RuntimeException {

    public MyCustomBadRequestException(String message) {
        super(message);
    }


}
