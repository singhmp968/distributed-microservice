package com.cc.orderService.bus;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

// Same SIMPLE name as product-service's MyRemoteCustomeEvent so the bus
// matches the event type across services (packages differ, that's fine).
public class MyRemoteCustomeEvent extends RemoteApplicationEvent {

    private String message;

    public MyRemoteCustomeEvent() {}   // no-arg ctor required for deserialization

    public MyRemoteCustomeEvent(Object source, String originService, String des, String message) {
        super(source, originService);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
