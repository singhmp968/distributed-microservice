package com.cc.productService.bus;

import com.cc.orderService.bus.OrderMessageEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderMessageListener {
    @EventListener
    public void handle(OrderMessageEvent event) {
        System.out.println(">>> product-service received from order: " + event.getMessage());
    }
}
