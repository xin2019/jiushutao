package com.xin.jiushutao.controller;

import com.xin.jiushutao.pojo.T_BUYER;
import com.xin.jiushutao.service.LoginService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/12 11:19
 * @Version 1.0
 **/
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/buyer/queryByBuyerName")
    public T_BUYER queryByBuyerName(@RequestParam("name") String name){
        return loginService.queryByBuyerName(name);
    }
    @GetMapping("/buyer/queryByBuyerPhone/{phone}")
    public T_BUYER queryByBuyerPhone(@PathVariable("phone") String phone){
        return loginService.queryByBuyerPhone(phone);
        //访问 http://localhost:9001/buyer/queryByBuyerPhone/15xxx005663
    }
    @GetMapping("/buyer/isExistBuyer")
    public boolean isExistBuyer(@Param("phone") String phone, @Param("password") String password){
        return loginService.isExistBuyer(phone,password);
        //访问：http://localhost:9001/buyer/isExistBuyer?phone=15395663&password=shayu
    }
    //访问：http://localhost:9001/buyer/isExistBuyer?phone=1535663&password=shayu
    @GetMapping("/buyer/list")
    private List<T_BUYER> list(){
        return loginService.queryAll();
    }

    @GetMapping("/buyer/queryIdByPhone")
//    @ResponseBody
    public String queryIdByPhone(@Param("phone") String phone){
        return loginService.queryIdByPhone(phone);
    }
    @GetMapping("/buyer/isRegister")
    @ResponseBody
    public boolean isRegister(String phone){
        return loginService.isRegister(phone);
    }

}
