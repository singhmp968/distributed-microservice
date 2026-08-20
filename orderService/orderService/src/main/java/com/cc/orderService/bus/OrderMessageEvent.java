package com.cc.orderService.bus;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

public class OrderMessageEvent extends RemoteApplicationEvent {
    private String message;
    public OrderMessageEvent() {}
    public OrderMessageEvent(Object source,String originService, String message) {
        super(source, originService);
        this.message = message;
    }
    public String getMessage() {return message;}
}
