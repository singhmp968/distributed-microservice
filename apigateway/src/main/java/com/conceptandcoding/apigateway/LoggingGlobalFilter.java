package com.conceptandcoding.apigateway;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

@Component
public class LoggingGlobalFilter implements GlobalFilter , Ordered {


    private static final Logger log = LoggerFactory.getLogger(LoggingGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long startTime = System.currentTimeMillis();
        System.out.println("LoggingGlobalFilter: " + exchange.getRequest().getURI().getPath());



        if(!exchange.getRequest().getHeaders().containsKey("Authorization"))
        {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String correlationId = java.util.UUID.randomUUID().toString();
        log.info("LoggingGlobalFilter: correlationId is " + correlationId);
        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                .header("X-Correlation-Id", correlationId)
                .build();
        ServerWebExchange mutatedExchange = exchange.mutate()
                .request(mutatedRequest)
                .build();
        return chain.filter(mutatedExchange)
                .then(Mono.fromRunnable(() -> {

            long duration = System.currentTimeMillis() - startTime;
            System.out.println("LoggingResponseGlobalFilter: " +
                    mutatedExchange.getResponse().getStatusCode() +" : "+ duration);
        }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
