package com.xin.jiushutao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/11 23:36
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
//熔断支持
@EnableCircuitBreaker
public class Provider_9001 {
    public static void main(String[] args) {
        SpringApplication.run(Provider_9001.class,args);
    }
}
