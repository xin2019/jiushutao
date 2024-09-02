package com.xin.jiushutao.service.impl;

import com.xin.jiushutao.dao.AdminDao;
import com.xin.jiushutao.pojo.T_ADMIN;
import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_SELLER;
import com.xin.jiushutao.pojo.T_SHOP;
import com.xin.jiushutao.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/6 20:40
 * @Version 1.0
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public int updateShopPriorityByShopid(String shopid, String priority) {
        return adminDao.updateShopPriorityByShopid(shopid,priority);
    }

    @Override
    public List<T_BOOK> allMainBoooks() {
        return adminDao.allMainBoooks();
    }

    @Override
    public List<T_BOOK> allRecomendBooks() {
        return adminDao.allRecomendBooks();
    }

    @Override
    public List<T_SHOP> allGoldShops() {
        return adminDao.allGoldShops();
    }

    @Override
    public int liftShop(String shopid) {

        return adminDao.liftShop(shopid);
    }

    @Override
    public T_SELLER getseller(String shopid) {
        return adminDao.getseller(shopid);
    }

    @Override
    public T_SHOP getshopByShopid(String shopid) {
        return adminDao.getshopByShopid(shopid);
    }

    @Override
    public int updatePriorityBook(String priority, String bookid) {
        return adminDao.updatePriorityBook(priority,bookid);
    }

    @Override
    public T_ADMIN getadmin(String phone) {
        return adminDao.getadmin(phone);
    }

    @Override
    public int receiveBookid(String bookid) {
        return adminDao.receiveBookid(bookid);
    }

    @Override
    public int receiveShopid(String shopid) {
        return adminDao.receiveShopid(shopid);
    }
}
