package com.cc.orderService.kafka.producer;

import com.cc.orderService.Entity.Order;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;



@Service
public class OrderProducerService {
    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;
    public void sendWithKey(Order order) {
        String key = order.getOrderId();
        CompletableFuture<SendResult<String,Order>> furure = kafkaTemplate.send("order-events",key,order);
        furure.whenComplete((r,e)->{
            if(e == null)
            {
                System.out.println("order event send success fullyorder.getOrderId()");

            }else{
                System.out.println("order event send failed fullyorder.getOrderId()" + e.getMessage());
            }
        });

    }
    public void sendWithOutKey(Order order) {

        String key = order.getOrderId();
        CompletableFuture<SendResult<String,Order>> furure = kafkaTemplate.send("order-event",key,order);
        furure.whenComplete((r,e)->{
            if(e == null)
            {
                System.out.println("order event send success fullyorder.getOrderId()");

            }else{
                System.out.println("order event send failed fullyorder.getOrderId()" + e.getMessage());
            }
        });
    }

    // one consumer and many topic

    public void sendPayment(Order order) {
        String key = order.getOrderId();
        kafkaTemplate.send("payments-events", key, order);
//        log.info("published to payments-events: {}", order.getOrderId());
    }


}
