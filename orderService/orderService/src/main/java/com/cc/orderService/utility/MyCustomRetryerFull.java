package com.cc.orderService.utility;

import feign.RetryableException;
import feign.Retryer;

public class MyCustomRetryerFull implements Retryer {
    private int attempt = 1;
    private final int maxAttempts = 5;
    private final long initialDelay = 1000; // 1 second

    @Override
    public void continueOrPropagate(RetryableException e) {
        if (attempt > maxAttempts) {
            System.out.println("Max retry attempts (" + maxAttempts + ") reached. Throwing exception.");
            throw e;
        }

        // Calculate exponential backoff: 1s, 2s, 4s, 8s, 16s
        long backoffDelay = initialDelay * (long) Math.pow(2, attempt - 1);

        System.out.println("Retry attempt: " + attempt + "/" + maxAttempts + " after " + backoffDelay + "ms due to: " + e.getMessage());
        attempt++;

        try {
            Thread.sleep(backoffDelay);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Retry interrupted", ignored);
        }

    }

    @Override
    public Retryer clone() {
        return new MyCustomRetryerFull();
    }
}
