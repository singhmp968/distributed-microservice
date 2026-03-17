package com.cc.orderService.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppCongig {


    @Bean(name = "restClientImpl")
    public RestClient restClientInstance(){
        return RestClient.create();    }


    @Bean(name = "simpleRestTemplate")
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
//
//
//
//    @Bean(name = "customRestTemplate")
//    public RestTemplate customrestTemplate() {
//
//        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//        factory.setConnectTimeout(3000);
//        factory.setReadTimeout(3000);
//        return new RestTemplate(factory);
//    }


}
