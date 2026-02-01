package com.cc.orderService.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;

import java.lang.reflect.Type;

public class MyCustomProductClientEncoder implements Encoder {
    @Override
    public void encode(Object o, Type type, RequestTemplate requestTemplate) throws EncodeException {
        try{
            String jsonString = new ObjectMapper().writeValueAsString(o);
            requestTemplate.body(jsonString);
        }catch(Exception e){
            throw new EncodeException(e.getMessage());
        }
    }
}
