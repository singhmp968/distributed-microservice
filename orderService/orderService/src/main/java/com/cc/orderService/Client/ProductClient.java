package com.cc.orderService.Client;

import com.cc.orderService.Dto.Product;
import com.cc.orderService.config.ProductClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service"
//        url="${feign.client.product-service.url}", // removing
        )

public interface ProductClient {
//    @GetMapping("/products/{id}")
//    String getProductDetails(@PathVariable("id") String id);
//
//    @PutMapping("/products/update/{id}")
//    Product updateProduct(@PathVariable("id") String id,
//                          @RequestBody String productDetails,
//                          @RequestParam("sendMail") boolean sendMail,
//                          @RequestHeader("X-ConceptCod-ID") String authToken);
//}



@GetMapping("/products/{id}")
String getProductDetails(@PathVariable("id") String id);

@PutMapping("/products/update/{id}")
Product updateProduct(@PathVariable("id") String id);
}

// below is without feing client

//@FeignClient(name = "product-service",
//        url="${feign.client.product-service.url}",
//        configuration = ProductClientConfig.class)
//
//public interface ProductClient {
//    @GetMapping("/products/{id}")
//    String getProductDetails(@PathVariable("id") String id);
//
//    @PutMapping("/products/update/{id}")
//    Product updateProduct(@PathVariable("id") String id,
//                          @RequestBody String productDetails,
//                          @RequestParam("sendMail") boolean sendMail,
//                          @RequestHeader("X-ConceptCod-ID") String authToken);
//}
