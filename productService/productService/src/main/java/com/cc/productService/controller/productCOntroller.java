package com.cc.productService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class productCOntroller {
    @GetMapping("/{id}")
    public String getProductDetails() throws InterruptedException {

        Thread.sleep(15_000); // 15 seconds

        return "Product details from product service";
    }
}
