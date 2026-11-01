package com.cc.productService.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {
    private static final Logger log = LoggerFactory.getLogger(OrderEventListener.class);
@KafkaListener(topics = "order-events",groupId = "product-service-group")
public void consume(String message) {
    log.info("productService received order event: {}", message);
}
}
