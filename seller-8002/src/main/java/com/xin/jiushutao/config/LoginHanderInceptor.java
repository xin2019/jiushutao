package com.xin.jiushutao.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/4 19:31
 * @Version 1.0
 **/
public class LoginHanderInceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sellerPhone =(String) request.getSession().getAttribute("sellerPhone");

        System.out.println("prehandle===>"+sellerPhone);
        if (sellerPhone!=null){
            return true;
        }

        response.sendRedirect("/");
        return false;
    }


}
