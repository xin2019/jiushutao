package com.xin.jiushutao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/4 15:08
 * @Version 1.0
 **/
@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/actuator/info").setViewName("login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHanderInceptor()).addPathPatterns("/**").excludePathPatterns("/","/login",
                "/js/**","/seller/enterRegister","/seller/register","/css/**","/bootstrap3/**","/seller/login","/view/**","/dist/**","/fonts/**","/images/**","/images/*","/sweetalert/**", "/static/upload/**");
    }

    @Bean
    public RestTemplate get(){
        return new RestTemplate();
    }

}
