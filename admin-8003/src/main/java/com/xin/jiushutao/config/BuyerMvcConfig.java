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
 * @Date 2021/2/13 10:56
 * @Version 1.0
 **/
@Configuration
public class BuyerMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/actuator/info").setViewName("login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInceptor()).addPathPatterns("/**").
                excludePathPatterns("/login.html","/","/login","/sweetalert/**","/bootstrap3/**","login","/css/**",
                        "/dist/**","/fonts/**","/images/**","/js/**");
    }
    @Bean
    public RestTemplate get(){
        return new RestTemplate();
    }
}
