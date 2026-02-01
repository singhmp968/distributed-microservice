package com.cc.productService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/products")
public class productCOntroller {
    @GetMapping("/{id}")
    public String getProductDetails() throws InterruptedException {

//        Thread.sleep(15_000); // 15 seconds

        return "Product details from product service";
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") String id,
                                                @RequestBody String productDetails,
                                                @RequestParam("sendMail") boolean sendMail,
                                                @RequestHeader("X-ConceptCod-ID") String authToken
                                                ) {
        return ResponseEntity.ok("Product updated successfully");
    }
}
