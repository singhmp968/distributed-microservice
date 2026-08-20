package com.cc.productService.bus;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

// Same SIMPLE name as orderService's OrderMessageEvent so Spring Cloud Bus
// can match the event type across services (packages may differ).
public class OrderMessageEvent extends RemoteApplicationEvent {
    private String message;

    public OrderMessageEvent() {}   // no-arg ctor required for deserialization

    public OrderMessageEvent(Object source, String originService, String message) {
        super(source, originService);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
