package com.cc.orderService.service;

import com.cc.orderService.Client.ProductClient;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.CompletableFuture;

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


//    Thread pool Bulkhead

    @Bulkhead(name = "puoductThreadPoolBulkHead",type = Bulkhead.Type.THREADPOOL,fallbackMethod = "bulkHeadThreadPoolFallBack")
    public CompletableFuture<String> invvokeNulkHeadThreadPool(@PathVariable String id)
    {
       return CompletableFuture.completedFuture(productClient.getProductDetails(id));

    }

    public CompletableFuture<String> bulkHeadThreadPoolFallBack(String id, Throwable t) {
        return CompletableFuture.completedFuture(
                "FALLBACK_REASON: " + t.getClass().getName() + " - " + t.getMessage());
    }


    // product Retry
    @Retry(name = "productRetry",fallbackMethod = "productServiceFallBack")
    public String invokeProductWithRetry(String id)
    {

        String response = productClient.getProductDetails(id);
        System.out.println("Response is " + response);
        return response;
    }

    private  String productServiceFallBack(String id, Throwable t)
    {
        System.out.println("Bulkhead Falling Back");
        return "RETRY_EXHAUSTED";
    }
}
