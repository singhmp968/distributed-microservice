package com.cc.orderService.kafka.customSeralizer;

import com.cc.orderService.Entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.json.JSONException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class OrderSUmmarySeralizer implements Serializer<Order>{
    private final ObjectMapper objectMapper;
    public OrderSUmmarySeralizer() {
        this.objectMapper = new ObjectMapper();
    }
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, Order order) {

        if(order==null){
            return null;
        }
        try{
            Map<String, Object> summary = new LinkedHashMap<>();
            summary.put("orderId", order.getOrderId());
            summary.put("productId",order.getCustomerName());
            return objectMapper.writeValueAsBytes(summary);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serilizer order summaryt", e);
        }
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Order data) {
        return Serializer.super.serialize(topic, headers, data);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
