package com.xin.jiushutao.service.impl;

import com.xin.jiushutao.dao.RegisterDao;
import com.xin.jiushutao.pojo.T_BUYER;
import com.xin.jiushutao.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/3 22:15
 * @Version 1.0
 **/
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterDao registerDao;

    @Override
    public boolean save(T_BUYER t_buyer) {
        return registerDao.save(t_buyer);
    }
}
