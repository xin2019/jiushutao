package com.xin.jiushutao.controller;

import com.xin.jiushutao.pojo.T_BUYER;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/3 22:33
 * @Version 1.0
 **/
@Controller
public class RegisterController {

    @Autowired
    private RestTemplate restTemplate;
    private String PREFIX="http://localhost:9001";
//private String PREFIX="http://60.204.157.187:9001";
    private static int buyerid=5;

//    name=a%E5%90%96&phone=%E5%95%8A&password=
//    %E5%95%8A&repassword=%E5%90%96&address=%E5%95%8A&email=%E5%90%96&sex=0
    @RequestMapping("/register")
    @ResponseBody
    public boolean register(HttpServletRequest request){
        T_BUYER t_buyer=new T_BUYER();
        String t_buyer_name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String sex = request.getParameter("sex");
        t_buyer.setT_buyer_address(address);
        t_buyer.setT_buyer_password(password);
        t_buyer.setT_buyer_sex(Boolean.parseBoolean(sex));
        t_buyer.setT_buyer_email(email);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddssmmhhyyyy");
        String format = simpleDateFormat.format(new Date());
        t_buyer.setT_buyer_id(String.valueOf(format));

        t_buyer.setT_buyer_name(t_buyer_name);
        t_buyer.setT_buyer_phone(phone);
        t_buyer.setT_cart_id("0");
        t_buyer.setT_order_id("0");
//        buyerid++;
        Boolean register = restTemplate.postForObject(PREFIX + "/register", t_buyer, Boolean.class);
        return register;
    }

    @RequestMapping("/registerWithgit")
    @ResponseBody
    public boolean registerWithgit(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        String githubname=null;
        if(cookies!=null)
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("githubName")){
                githubname=cookie.getValue();
            }
        }
        T_BUYER t_buyer=new T_BUYER();
        String t_buyer_name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String sex = request.getParameter("sex");
        t_buyer.setT_buyer_address(address);
        t_buyer.setT_github_user(githubname);
        t_buyer.setT_buyer_password(password);
        t_buyer.setT_buyer_sex(Boolean.parseBoolean(sex));
        t_buyer.setT_buyer_email(email);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddssmmhhyyyy");
        String format = simpleDateFormat.format(new Date());
        t_buyer.setT_buyer_id(String.valueOf(format));

        t_buyer.setT_buyer_name(t_buyer_name);
        t_buyer.setT_buyer_phone(phone);
        t_buyer.setT_cart_id("0");
        t_buyer.setT_order_id("0");
//        buyerid++;
        Boolean register = restTemplate.postForObject(PREFIX + "/registerWithGit", t_buyer, Boolean.class);

        Cookie userPhone=new Cookie("userPhone",phone);
        userPhone.setPath("/");
        userPhone.setMaxAge(60*60*24*1000);
        response.addCookie(userPhone);
        return register;
    }


}
