package com.xin.jiushutao.controller;

import com.xin.jiushutao.service.AdminLoginService;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/7 8:43
 * @Version 1.0
 **/
@RestController
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminLoginService;

    @RequestMapping("/validateAdmin")
    public boolean validateAdmin(@Param("phone")String phone,@Param("password")String password){
        return adminLoginService.validateAdmin(phone,password);
    }
}
