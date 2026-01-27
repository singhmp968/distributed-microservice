package com.cc.orderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;


@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    @Qualifier("restClientImpl")
    RestClient restClient;

//    @GetMapping("/{id}")
//    public ResponseEntity<String> getOrderDetails(@PathVariable("id") Long orderId) {
//
//        String responseObj = restClient.get()
//                .uri("http://localhost:8082/products/" + orderId)
//                .retrieve()
//                // handling exceptions can be added here
//                .onStatus(response ->{
//                    if(response.getStatusCode().is4xxClientError()){
//                        throw new RuntimeException("4xx error");
//                    } else if (response.getStatusCode().is5xxServerError()) {
//                        System.out.println("5xx error occurred while calling product service");
//                    }
//                    return false;
//                })
//                .body(String.class);
//
////        String response = restTemplate.getForObject("http://localhost:8082/products/" + orderId, String.class);
//        System.out.println("Response from product service: " + responseObj);
//        return ResponseEntity.ok("Order details for order id: " + orderId);
//    }


@GetMapping("/{id}")
public ResponseEntity<String> getOrderDetails(@PathVariable("id") Long orderId) {

    String responseObj = restClient.get()
            .uri("http://localhost:8082/products/" + orderId)
            .exchange((request, response) -> {

                if(response.getStatusCode().is4xxClientError()){
                    throw new RuntimeException("4xx error");
                } else if (response.getStatusCode().is5xxServerError()) {
                    System.out.println("5xx error occurred while calling product service");
                }
                return StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);

            });

//        String response = restTemplate.getForObject("http://localhost:8082/products/" + orderId, String.class);
        System.out.println("Response from product service: " + responseObj);
        return ResponseEntity.ok("Order details for order id: " + orderId);
}

}
