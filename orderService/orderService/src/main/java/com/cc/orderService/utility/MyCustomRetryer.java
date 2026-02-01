package com.cc.orderService.utility;

import feign.Retryer;

public class MyCustomRetryer extends Retryer.Default {
    public MyCustomRetryer() {
        super(200, 1000, 3);
    }
}
