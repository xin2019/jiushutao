package com.xin.jiushutao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2022/3/3 16:10
 * @Version 1.0
 **/
@Controller
public class showAdminpages {
    @RequestMapping("/showadminpages")
    public String showadminpages(){
        return "admin_introduce";
    }
}
