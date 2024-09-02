package com.xin.jiushutao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/6 20:27
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
//熔断支持
@EnableCircuitBreaker
public class ProviderAdmin9003 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderAdmin9003.class,args);
    }
}
