package com.cc.orderService.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MyCustomBadRequestException.class)
    public ResponseEntity<String> handleCustomerAlreadyExistsException(MyCustomBadRequestException ex,
                                                                          WebRequest webRequest) {
    return new ResponseEntity<>(ex.getMessage(),  org.springframework.http.HttpStatus.BAD_REQUEST);
    }
}
