package com.xin.jiushutao.service;

import com.xin.jiushutao.pojo.T_BUYER;
import com.xin.jiushutao.pojo.T_ORDER;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/12 11:15
 * @Version 1.0
 **/
public interface LoginService {
    public T_BUYER queryByBuyerName(String name); //通过买家昵称查询该用户,使用jpa或者redis，建议redis

    public T_BUYER queryByBuyerPhone(String phone); //通过买家手机号查询该用户

    public boolean isExistBuyer(String phone,String password); //通过用户名和密码验证是否为合法买家

    public List<T_BUYER> queryAll();//查询所有买家

    public String queryIdByPhone(String phone);//通过唯一手机号查询id

    public boolean isRegister(String phone);//查询该手机号是否已经注册


}
