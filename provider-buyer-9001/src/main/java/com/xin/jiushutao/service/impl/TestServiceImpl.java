package com.xin.jiushutao.service.impl;

import com.netflix.discovery.converters.Auto;
import com.xin.jiushutao.dao.TestDao;
import com.xin.jiushutao.proxy.buyer.OrderAndBuyer;
import com.xin.jiushutao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/21 14:25
 * @Version 1.0
 **/
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;
    @Override
    public OrderAndBuyer get(String t_order_id) {
        return testDao.get(t_order_id);
    }
}
