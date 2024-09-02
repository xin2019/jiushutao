package com.xin.jiushutao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/7 8:28
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
public class Admin8003 {
    public static void main(String[] args) {
        SpringApplication.run(Admin8003.class,args);
    }
}
