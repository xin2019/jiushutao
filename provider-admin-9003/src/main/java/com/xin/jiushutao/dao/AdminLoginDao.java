package com.xin.jiushutao.dao;

import com.xin.jiushutao.pojo.T_ADMIN;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/7 8:46
 * @Version 1.0
 **/
@Mapper
@Repository
public interface AdminLoginDao {
    /*
    验证是否为合法管理员
     */
    public T_ADMIN validateAdmin(@Param("phone") String phone, @Param("password") String password);
}
