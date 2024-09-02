package com.xin.jiushutao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/4 14:16
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
public class Seller8002 {
    public static void main(String[] args) {
        SpringApplication.run(Seller8002.class,args);
    }
}
