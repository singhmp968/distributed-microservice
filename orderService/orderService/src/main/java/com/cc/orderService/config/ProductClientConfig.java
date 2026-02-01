package com.cc.orderService.config;

import com.cc.orderService.utility.MyCustomProductClientDecoder;
import com.cc.orderService.utility.MyCustomProductClientEncoder;
import com.cc.orderService.utility.MyCustomRetryerFull;
import com.cc.orderService.utility.myCustomErrorDecoder;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class ProductClientConfig {
//    @Bean
//    public Retryer defaultRetryer() {
//        // initial 100ms, max 1 second, max 5 attempts
//        return new Retryer.Default(200, 1000, 5);
//    }
//    @Bean
//    public Retryer MyCustomRetryer() {
//        return new Retryer.Default(100, 1000, 3);
//    }

    @Bean
    public Retryer MyCustomRetryerFullCtrl() {
        return new MyCustomRetryerFull();
    }

    @Bean
    public Encoder myCustomEncoder() {
        return new MyCustomProductClientEncoder();
    }
    @Bean
    public Decoder myCustomDecoder() {
        return new MyCustomProductClientDecoder();
    }
    @Bean
    public ErrorDecoder errorDecoder() {
        return new myCustomErrorDecoder();
    }


}
