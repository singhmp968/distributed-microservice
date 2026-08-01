package com.cc.orderService.service;

import com.cc.orderService.Client.ProductClient;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    @Autowired
    ProductClient productClient;

    @RateLimiter(name = "productRateLimiter", fallbackMethod = "rateLimiterFallBack")
    public String invokeProductApi(String id)
    {
        String response = productClient.getProductDetails(id);
        System.out.println("Response is " + response);
        return response;

    }


    public String rateLimiterFallBack(String id,Throwable t)
    {
        System.out.println("Rate Limiter Falling Back");
        // thrwo exception
        return "RATE_LIMITED";
    }
// BulkHead Pattern
    @Bulkhead(name = "productBulkHead",
            type = Bulkhead.Type.SEMAPHORE,
            fallbackMethod = "bulkHeadFallBack")
    public String invalkeBulkHeadPatterFromOrderTOProduct(String id)
    {
        String response = productClient.getProductDetails(id);
        System.out.println("Response is " + response);
        return response;

    }
    public String bulkHeadFallBack(String id, Throwable t)     // add , Throwable t
    {
        System.out.println("Bulkhead Falling Back");
        return "BULKHEAD_FALLING";
    }

}
