package com.xin.jiushutao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/12 17:26
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaServer
public class Eureka7003 {
    public static void main(String[] args) {
        SpringApplication.run(Eureka7003.class,args);
    }
}
