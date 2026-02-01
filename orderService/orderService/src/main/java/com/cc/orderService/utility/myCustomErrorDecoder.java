package com.cc.orderService.utility;

import com.cc.orderService.exception.MyCustomBadRequestException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class myCustomErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();
    @Override
    public Exception decode(String s, Response response) {
        HttpStatus statusCode = HttpStatus.valueOf(response.status());
        if(statusCode.is4xxClientError()) {
            // 4xx errors are client errors - don't retry
            return new MyCustomBadRequestException("Custom error message for status code: " + response.status());
        }
        if(statusCode.is5xxServerError()) {
            // 5xx errors should be retryable
            System.out.println("5xx error detected: " + response.status() + ". Creating RetryableException for retry logic.");
            return new feign.RetryableException(
                    response.status(),
                    "5xx Server Error - will retry. Status: " + response.status(),
                    response.request().httpMethod(),
                    (java.util.Date) null,
                    response.request()
            );
        }

        return defaultErrorDecoder.decode(s, response);

    }
}
