package com.cc.productService.controller;

import com.cc.productService.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/products")
public class productCOntroller {
    @GetMapping("/{id}")
    public String getProductDetails() throws InterruptedException {

//        Thread.sleep(15_000); // 15 seconds
//        Thread.sleep(2000); // 15 seconds
//
//        return "Product details from product service";


//        System.out.println("🔥 Product HIT at " + java.time.LocalTime.now());
//        throw new RuntimeException("Simulated transient failure");
        return "hurryyyyy";
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id,
                                                 @RequestBody String productDetails,
                                                 @RequestParam("sendMail") boolean sendMail,
                                                 @RequestHeader("X-ConceptCod-ID") String authToken
                                                ) {
//        return ResponseEntity.ok("Product updated successfully");
//        return ResponseEntity.status(400).body(null);

        System.out.println("🔥 Product service called for id: " + id);
        throw new RuntimeException("Simulated failure for retry demo");
    }
}
