package com.xin.jiushutao.service.impl;

import com.xin.jiushutao.dao.AdminLoginDao;
import com.xin.jiushutao.pojo.T_ADMIN;
import com.xin.jiushutao.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/7 8:48
 * @Version 1.0
 **/
@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    private AdminLoginDao loginDao;

    @Override
    public boolean validateAdmin(String phone, String password) {
        T_ADMIN t_admin = loginDao.validateAdmin(phone, password);
        return t_admin==null?false:true;
    }
}
