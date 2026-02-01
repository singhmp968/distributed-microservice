package com.cc.orderService.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service",url="${feign.client.product-service.url}")
public interface ProductClient {
    @GetMapping("/products/{id}")
    String getProductDetails(@PathVariable("id") String id);

    @PutMapping("/products/update/{id}")
    ResponseEntity<String> updateProduct(@PathVariable("id") String id,
                                         @RequestBody String productDetails,
                                         @RequestParam("sendMail") boolean sendMail,
                                         @RequestHeader("X-ConceptCod-ID") String authToken);
}
