package com.xin.jiushutao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/4 16:29
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
//熔断支持
@EnableCircuitBreaker
public class ProviderSeller9002 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderSeller9002.class,args);
    }
}
