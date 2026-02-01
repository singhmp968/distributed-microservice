package com.cc.orderService.controller;

import com.cc.orderService.Client.ProductClient;
import com.cc.orderService.Dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;


@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    @Qualifier("restClientImpl")
    RestClient restClient;

    @Autowired
    ProductClient productClient;
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
@GetMapping("/feign/{id}")
public ResponseEntity<String> getOrderDetailsFeign(@PathVariable("id") Long orderId){
    try {
        String responseObj = productClient.getProductDetails(orderId.toString());
        System.out.println("Response from product service using feign client: " + responseObj);
        return ResponseEntity.ok("Order details for order id: " + orderId);
    } catch (Exception e) {
        System.err.println("Feign client error after retries exhausted: " + e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(503).body("Service unavailable - Retries exhausted: " + e.getMessage());
    }
}
@PutMapping(value = "/feign/update/{id}",consumes = "application/json")
public ResponseEntity<String> updateProductFeign(@PathVariable("id") Long productId,
                                                 @RequestBody String productDetails,
                                                 @RequestParam("sendMail") boolean sendMail,
                                                 @RequestHeader("X-ConceptCod-ID") String authToken){
    Product response = productClient.updateProduct(productId.toString(), productDetails, sendMail, authToken);

    return ResponseEntity.ok("Order details for order id: " + productId);
}

}
