package com.xin.jiushutao.service;

import com.xin.jiushutao.pojo.T_BUYER;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/3 22:13
 * @Version 1.0
 **/

public interface RegisterService {
    /*
   注册新的买家用户
    */
    public boolean save(T_BUYER t_buyer);
}
