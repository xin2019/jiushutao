package com.xin.jiushutao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:static/");
//        registry.addResourceHandler("/static/upload/**").addResourceLocations("file:D:\\STUDY\\bishe\\version14\\jiushutao\\buyer-8001\\src\\main\\resources\\static\\upload\\");
//        registry.addResourceHandler("/upload/**").addResourceLocations("file:D:\\STUDY\\bishe\\version14\\jiushutao\\buyer-8001\\src\\main\\resources\\static\\upload\\");

//        registry.addResourceHandler("/static/upload/**").addResourceLocations("/home/xin/jiushutao/upload/");
//        registry.addResourceHandler("/upload/**").addResourceLocations("/home/xin/jiushutao/upload/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/actuator/info").setViewName("index");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInceptor()).addPathPatterns("/**").
                excludePathPatterns("/index.html","/buyer/product/getGoldShops","/buyer/product/getCategoryBooks","/buyer/product/shopDetail","/buyer/product/getMoreGoodBooks","/buyer/product/getRecomendBooks","/buyer/product/getMainbooks","/register","/buyer/product/searchBooks","/upload/**","upload/first_priority","/upload/first_priority/","/upload/first_priority/*","/sweetalert/**","/tt","/bootstrap3/**","/buyer/loginBefore","/test","/buyer/getUsername","/buyer/product/allbooks","/buyer/product/detail","/buyer/product/allBooks","index","/css/**",
                        "/dist/**","/sellerpages/**","/showarchitect/**","/showadminpages/**","/fonts/**","/images/**","/js/**","/loginGithub/**","/registerWithgit/**","https://github.com/**","/account/github/**","https://api.github.com/**","/callback/**");
    }
    @Bean
    public RestTemplate get(){
        return new RestTemplate();
    }
}
