package com.cc.orderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    @Qualifier("customRestTemplate")
    RestTemplate restTemplate;

    @GetMapping("/{id}")
    public ResponseEntity<String> getOrderDetails(@PathVariable("id") Long orderId) {


        String response = restTemplate.getForObject("http://localhost:8082/products/" + orderId, String.class);
        System.out.println("Response from product service: " + response);
        return ResponseEntity.ok("Order details for order id: " + orderId);
    }

}
