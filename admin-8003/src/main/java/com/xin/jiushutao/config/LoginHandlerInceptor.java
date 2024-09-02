package com.xin.jiushutao.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: springboot04
 * @Author 陈欣
 * @description
 * @Date 2021/1/24 18:14
 * @Version 1.0
 **/
public class LoginHandlerInceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
//        Object adminPhone =request.getSession().getAttribute("adminPhone");
//        if(adminPhone==null){
//            response.sendRedirect("/");
//            return false;
//        }
//        else{
//            return true;
//        }
        return true;

    }
}
