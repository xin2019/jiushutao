package com.xin.jiushutao.service;

import com.xin.jiushutao.proxy.buyer.OrderAndBuyer;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/21 14:25
 * @Version 1.0
 **/
public interface TestService {
    public OrderAndBuyer get(String t_order_id);
}
