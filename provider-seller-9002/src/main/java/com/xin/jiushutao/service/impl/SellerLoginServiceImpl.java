package com.xin.jiushutao.service.impl;

import com.xin.jiushutao.dao.SellerLoginDao;
import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_ORDER;
import com.xin.jiushutao.pojo.T_SELLER;
import com.xin.jiushutao.pojo.T_SHOP;
import com.xin.jiushutao.service.SellerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/4 19:04
 * @Version 1.0
 **/
@Service
public class SellerLoginServiceImpl implements SellerLoginService {
    @Autowired
    private SellerLoginDao sellerLoginDao;

    @Override
    public boolean addNewSeller(T_SELLER t_seller) {
        return sellerLoginDao.addNewSeller(t_seller);
    }

    @Override
    public int updateStatusByBookidAndStatus(String bookid, int status) {
        return sellerLoginDao.updateStatusByBookidAndStatus(bookid,status);
    }

    @Override
    public T_ORDER getOrderByBookid(String bookid) {
        return sellerLoginDao.getOrderByBookid(bookid);
    }

    @Override
    public List<T_BOOK> getAllBooksPurchase(String sellerid, int status) {
        System.out.println("9002-----------sellerid==>"+sellerid+",,,+status-->"+status);
        return sellerLoginDao.getAllBooksPurchase(sellerid,status);
    }


    @Override
    public boolean isRightSeller(String phone, String password) {
        return sellerLoginDao.isRightSeller(phone,password)==null?false:true;
    }

    @Override
    public T_SELLER getSeller(String phone) {
        return sellerLoginDao.getSeller(phone);
    }

    @Override
    public T_SHOP getShop(String shopid) {
        return sellerLoginDao.getShop(shopid);
    }

    @Override
    public List<T_BOOK> getAllBooksByShopId(String shopid) {
        return sellerLoginDao.getAllBooksByShopId(shopid);
    }

    @Override
    public boolean updateShopnameByShopid(String shopid, String shopname) {
        return sellerLoginDao.updateShopnameByShopid(shopid,shopname);
    }

    /*
    通过bookid下架book
     */
    @Override
    public boolean rackBookByBookid(String bookid){
        return sellerLoginDao.rackBookByBookid(bookid);
    }

    @Override
    public boolean addNewBook(T_BOOK t_book) {
        return  sellerLoginDao.addNewBook(t_book);
    }

    @Override
    public boolean addNewShop(T_SHOP t_shop) {
        return sellerLoginDao.addNewShop(t_shop);
    }

}
