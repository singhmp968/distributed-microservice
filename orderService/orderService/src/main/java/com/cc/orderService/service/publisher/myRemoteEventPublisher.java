package com.cc.orderService.service.publisher;

import com.cc.orderService.bus.MyRemoteCustomeEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class myRemoteEventPublisher {


    @Autowired
    private ApplicationEventPublisher publisher;

    @Value("${spring.cloud.bus.id}")
    String myBusId;
//    @Value("${spring.cloud.bus.name}")
//    String myBusName;
    public String publisher(String msg) {
        publisher.publishEvent(new MyRemoteCustomeEvent(this,myBusId,"*",msg));
        return "sent";
    }

}
