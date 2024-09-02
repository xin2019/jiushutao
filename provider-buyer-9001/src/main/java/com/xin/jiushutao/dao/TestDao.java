package com.xin.jiushutao.dao;

import com.xin.jiushutao.proxy.buyer.OrderAndBuyer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/21 14:22
 * @Version 1.0
 **/
@Mapper
@Repository
public interface TestDao {
    public OrderAndBuyer get(String t_order_id);
}
