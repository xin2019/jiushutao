package com.xin.jiushutao.service.impl;

import com.xin.jiushutao.dao.LoginDao;
import com.xin.jiushutao.pojo.T_BUYER;
import com.xin.jiushutao.pojo.T_ORDER;
import com.xin.jiushutao.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/12 11:16
 * @Version 1.0
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public T_BUYER queryByBuyerName(String name) {
        return loginDao.queryByBuyerName(name);
    }

    @Override
    public T_BUYER queryByBuyerPhone(String phone) {
        return loginDao.queryByBuyerPhone(phone);
    }

    @Override
    public boolean isExistBuyer(String phone, String password) {
        Map map=new HashMap();
        map.put("phone",phone);
        map.put("password",password);
        T_BUYER existBuyer = loginDao.isExistBuyer(map);
        return existBuyer==null?false:true;
    }

    @Override
    public List<T_BUYER> queryAll() {
        return loginDao.queryAll();
    }

    @Override
    public String queryIdByPhone(String phone) {
        T_BUYER t_buyer = loginDao.queryIdByPhone(phone);
        return t_buyer==null?"NULL":t_buyer.getT_buyer_id();
    }

    @Override
    public boolean isRegister(String phone) {
        return loginDao.isRegister(phone)==null?false:true;
    }


}
