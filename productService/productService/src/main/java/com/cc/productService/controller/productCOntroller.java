package com.cc.productService.controller;

import com.cc.productService.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/products")
public class productCOntroller {
    int counter=0;


//    @Autowired
//    ProductProperities productProperities;
//
//    @GetMapping("/config-message")
//    public String configMessage() {
//        return "product-service: " + productProperities.getMessage();
//    }


    @GetMapping("/{id}")
    public String getProductDetails(@RequestHeader(value="X-Correlation-Id", required=false) String cid,
                                    @RequestHeader(value="X-test-RequestHeader", required=false) String testHeader
                                    ) throws InterruptedException {

//        Thread.sleep(15_000); // 15 seconds
//        Thread.sleep(2000); // 15 seconds
//
//        return "Product details from product service";


//        System.out.println("🔥 Product HIT at " + java.time.LocalTime.now());
//        throw new RuntimeException("Simulated transient failure");
        System.out.println("testHeader:  " + testHeader);

//        System.out.println("productService correlationId = " + cid);
//        return "hurryyyyy";

        // retyr check
        counter++;
        if(counter<=3)
        {
        throw new RuntimeException("Simulated transient failure");

        }
        return "success in 4th attempt";

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
