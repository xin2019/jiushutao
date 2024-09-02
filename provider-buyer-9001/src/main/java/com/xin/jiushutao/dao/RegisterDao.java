package com.xin.jiushutao.dao;

import com.xin.jiushutao.pojo.T_BUYER;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/12 11:01
 * @Version 1.0
 **/
@Mapper
@Repository
public interface RegisterDao {

    /*
    注册新的买家用户
     */
    public boolean save(T_BUYER t_buyer);
}
