package com.xin.jiushutao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2022/3/3 19:35
 * @Version 1.0
 **/
@Controller
public class showArchitect {
    @RequestMapping("/showarchitect")
    public String show(){
        return "architect_introduce";
    }
}
