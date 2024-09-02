package com.xin.jiushutao.service.impl;

import com.netflix.discovery.converters.Auto;
import com.xin.jiushutao.dao.OrderDao;
import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_BUYER;
import com.xin.jiushutao.pojo.T_ORDER;
import com.xin.jiushutao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/15 16:40
 * @Version 1.0
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<T_ORDER> allOrder(String id) {
        return orderDao.allOrder(id);
    }

    @Override
    public String queryShopNameByShopId(String shopId) {
        System.out.println("orderService==>shopId====="+shopId);
        String s = orderDao.queryShopNameByShopId(shopId);
        System.out.println("orderDao.queryShopNameByShopId(shopId)调用结果：："+s);
        return s;
    }

    @Override
    public List<String> getAllShopIdByBuyerId(String id) {
        return orderDao.getAllShopIdByBuyerId(id);
    }

    @Override
    public int updateStatusByBookid(String bookid, int status) {
        System.out.println("jinrule--------9001 impl");
        System.out.println("bookid=="+bookid+",---status==>"+status);
        return orderDao.updateStatusByOrderid(bookid,status);
    }

    @Override
    public String getBookidByOrderid(String orderid) {
        return orderDao.getBookidByOrderid(orderid);
    }

    @Override
    public int updateStatusByOrderid(String orderid, int status) {
        return orderDao.updateStatusByOrderid(orderid,status);
    }


    @Override
    public T_BOOK findByBookId(String bookid) {
        return orderDao.findByBookId(bookid);
    }

    @Override
    public boolean deleteOrderByOrderId(String id) {
        return orderDao.deleteOrderByOrderId(id);
    }

    @Override
    public T_BUYER getBuyerByOrderId(String t_order_id) {
        return orderDao.getTBuyerById(t_order_id);
    }

    @Override
    public boolean insertNewOrder(T_ORDER t_order) {
        return orderDao.insertNewOrder(t_order);
    }


    @Override
    public boolean deleteBookByBookId(String bookid) {
        return orderDao.deleteBookByBookId(bookid);
    }

    /*
      通过buyerid返回
       */
    @Override
    public String getSelleridByShopId(String shopid) {
        return orderDao.getSelleridByShopId(shopid);
    }




}
