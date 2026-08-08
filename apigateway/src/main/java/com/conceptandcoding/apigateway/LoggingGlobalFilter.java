package com.conceptandcoding.apigateway;


import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

@Component
public class LoggingGlobalFilter implements GlobalFilter , Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("LoggingGlobalFilter: " + exchange.getRequest().getURI().getPath());

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            System.out.println("LoggingResponseGlobalFilter: " +
                    exchange.getResponse().getStatusCode());
        }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
