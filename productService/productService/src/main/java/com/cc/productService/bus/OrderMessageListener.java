package com.cc.productService.bus;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderMessageListener {
    @EventListener
    public void handle(OrderMessageEvent event) {
        System.out.println(">>> product-service received from order: " + event.getMessage());
    }

    @EventListener
    public void handleRemoteOrdListener(MyRemoteCustomeEvent event) {
        System.out.println(">>> product-service received from order: " + event.getMessage());
    }


}
