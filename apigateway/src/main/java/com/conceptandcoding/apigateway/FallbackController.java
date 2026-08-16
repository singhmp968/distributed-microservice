package com.conceptandcoding.apigateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
    @GetMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("Product service is unavailable right now. Please try later.");
    }
}
