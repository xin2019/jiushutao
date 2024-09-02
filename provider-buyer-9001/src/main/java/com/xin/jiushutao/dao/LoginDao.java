package com.xin.jiushutao.dao;

import com.xin.jiushutao.pojo.T_BUYER;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/12 0:22
 * @Version 1.0
 **/
@Mapper
@Repository
public interface LoginDao {

    public T_BUYER queryByBuyerName(String name); //通过买家昵称查询该用户,使用jpa或者redis，建议redis

    public T_BUYER queryByBuyerPhone(String phone); //通过买家手机号查询该用户

    public T_BUYER isExistBuyer(Map map); //通过用户名和密码验证是否为合法买家

    public List<T_BUYER> queryAll();//查询所有买家

    public T_BUYER queryIdByPhone(String phone);//通过唯一手机号查询id

    public T_BUYER isRegister(String phone);//查询该手机号是否已经注册



}
