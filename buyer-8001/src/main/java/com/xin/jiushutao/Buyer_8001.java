package com.xin.jiushutao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/13 10:32
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
public class Buyer_8001 {
    public static void main(String[] args) {
        SpringApplication.run(Buyer_8001.class,args);
    }
}
