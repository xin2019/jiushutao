package com.xin.jiushutao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
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

        String userPhone=null;
        String githubUserName=null;
        Cookie[] cookies=request.getCookies();
        if(cookies!=null&&cookies.length>0){
            for (Cookie cookie:cookies){
                if(cookie.getName().equals("userPhone")){
                    userPhone=cookie.getValue();
                }
                if(cookie.getName().equals("githubName")){
                    githubUserName=cookie.getValue();
                }
            }
        }

        //查询成功后应该有用户session
//        Object loginUser=request.getSession().getAttribute("loginUser");
        if(userPhone!=null||userPhone!=""||githubUserName!=""||githubUserName!=null){//没有登录
            //正常业务代码
            return true;
        }else{


            response.sendRedirect("/index.html");
//            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;

        }
    }
}
