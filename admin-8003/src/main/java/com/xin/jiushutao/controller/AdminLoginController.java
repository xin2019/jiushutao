package com.xin.jiushutao.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/7 8:39
 * @Version 1.0
 **/
@Controller
@RequestMapping("/admin")
public class AdminLoginController {
    @Autowired
    private RestTemplate restTemplate;
    private String PREFIX="http://localhost:9003";

    @RequestMapping("/login")
    @ResponseBody
    public Boolean login(@Param("phone")String phone, @Param("password")String password, HttpServletRequest request){
        request.getSession(true).setAttribute("adminPhone",phone);

        Boolean validate = restTemplate.getForObject(PREFIX + "admin/validateAdmin?phone=" + phone + "&password=" + password, Boolean.class);
        return validate;
    }

    @RequestMapping("/enterAdmin")
    public String enter(HttpSession session, Model model){
        Object adminPhone = session.getAttribute("adminPhone");
        model.addAttribute("phone",adminPhone);
        return "index";
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.removeAttribute("adminPhone");
        response.sendRedirect("/");
    }
}
