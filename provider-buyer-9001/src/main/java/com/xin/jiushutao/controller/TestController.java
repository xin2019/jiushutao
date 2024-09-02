package com.xin.jiushutao.controller;

import com.xin.jiushutao.proxy.buyer.OrderAndBuyer;
import com.xin.jiushutao.service.TestService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/21 14:26
 * @Version 1.0
 **/
@Controller
@ResponseBody
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "test";
    }


    @Autowired
    private TestService testService;
    @RequestMapping("/99test")
    public OrderAndBuyer get(@Param("id") String id) {
        return testService.get(id);
    }
}
