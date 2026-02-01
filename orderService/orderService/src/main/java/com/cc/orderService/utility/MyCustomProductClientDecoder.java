package com.cc.orderService.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

public class MyCustomProductClientDecoder implements Decoder {
    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        InputStream responseBodyStream = response.body().asInputStream();
        return new ObjectMapper().readValue(responseBodyStream,new TypeReference<Object>() {
            @Override
            public Type getType() {
                return type;
            }
        });
    }
}
