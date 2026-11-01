package com.cc.productService.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {
    private static final Logger log = LoggerFactory.getLogger(OrderEventListener.class);
//@KafkaListener(topics = "order-events",groupId = "product-service-group")
//public void consume(String message) {
//    log.info("productService received order event: {}", message);
//}

    // one consumenr multiple topic manual mapping approache 
    @KafkaListener(topics = {"order-events","payments-events"},groupId = "product-service-group")
    public void consume(ConsumerRecord<String,String> record) {
//        log.info("productService received order event: {}", record);
    if("order-events".equals(record.topic())){
        log.info("Order event received: {}", record.value());
    }
    else if ("payments-events".equals(record.topic())) {
        log.info("payments event received: {}", record.value());     // line 25

    }

    }

}
