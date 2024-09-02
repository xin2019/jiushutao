package com.xin.jiushutao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2022/3/2 18:42
 * @Version 1.0
 **/
@Controller
public class showSellerpages {
    @RequestMapping("/sellerpages")
    public String showsellerpages(){
        return "seller_introduce";
    }
}
