package com.xin.jiushutao.service.impl;

import com.netflix.discovery.converters.Auto;
import com.xin.jiushutao.dao.CartDao;
import com.xin.jiushutao.pojo.T_CART;
import com.xin.jiushutao.proxy.buyer.CartAndBook;
import com.xin.jiushutao.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/28 22:00
 * @Version 1.0
 **/
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;
    @Override
    public CartAndBook getBooksByBuyerID(String t_buyer_id) {
        System.out.println("9001-CartServiceImpl");
        return cartDao.getBooksByBuyerID(t_buyer_id);
    }

    @Override
    public boolean addToCart(String bookid, String buyerid, int quantity) {
        return cartDao.addToCart(bookid,buyerid,quantity);
    }

    @Override
    public List<T_CART> getAllBookIdByBuyerid(String buyerid) {
        return cartDao.getAllBookIdByBuyerid(buyerid);
    }

    @Override
    public boolean deleteCartRecordByBookId(String bookid) {
        return cartDao.deleteCartRecordByBookId(bookid);
    }


}
