package com.xin.jiushutao.service;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/7 8:48
 * @Version 1.0
 **/
public interface AdminLoginService {
    /*
   验证是否为合法管理员
    */
    public boolean validateAdmin(String phone,String password);
}
