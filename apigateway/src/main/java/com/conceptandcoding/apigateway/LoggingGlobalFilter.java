package com.conceptandcoding.apigateway;


import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

@Component
public class LoggingGlobalFilter implements GlobalFilter , Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long startTime = System.currentTimeMillis();
        System.out.println("LoggingGlobalFilter: " + exchange.getRequest().getURI().getPath());

        if(!exchange.getRequest().getHeaders().containsKey("Authorization"))
        {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {

            long duration = System.currentTimeMillis() - startTime;
            System.out.println("LoggingResponseGlobalFilter: " +
                    exchange.getResponse().getStatusCode() +" : "+ duration);
        }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
