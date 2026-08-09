package com.cc.orderService.service;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class CorrelationIdFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes!=null){
            String correlationId = attributes.getRequest().getHeader("X-Correlation-Id");
            System.out.println("correlationId is " + correlationId);

            if (correlationId != null) {
                requestTemplate.header("X-Correlation-Id", correlationId);   // <-- FORWARD it
                System.out.println("Feign forwarding correlationId = " + correlationId);
            }

        }

    }
}
