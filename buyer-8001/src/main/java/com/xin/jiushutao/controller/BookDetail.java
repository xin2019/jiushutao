package com.xin.jiushutao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/17 9:58
 * @Version 1.0
 **/
@Controller
public class BookDetail {

    @RequestMapping("/tt")
    public String bookDetail(){
        return "tt";
    }
}
