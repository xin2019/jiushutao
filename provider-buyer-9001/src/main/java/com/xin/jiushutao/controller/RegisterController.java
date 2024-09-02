package com.xin.jiushutao.controller;

import com.xin.jiushutao.dao.RegisterDao;
import com.xin.jiushutao.pojo.T_BUYER;
import com.xin.jiushutao.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/3 22:25
 * @Version 1.0
 **/
@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    /*
  注册新的买家用户
   */
    @RequestMapping("/register")
    public boolean save(@RequestBody T_BUYER t_buyer){
        System.out.println("9001------------");
        System.out.println(t_buyer);
        return registerService.save(t_buyer);
    }
}
