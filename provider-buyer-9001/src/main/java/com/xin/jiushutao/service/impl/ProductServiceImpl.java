package com.xin.jiushutao.service.impl;

import com.xin.jiushutao.dao.ProductDao;
import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_SHOP;
import com.xin.jiushutao.proxy.buyer.BookAndShop;
import com.xin.jiushutao.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/18 10:35
 * @Version 1.0
 **/
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;


    @Override
    public List<T_BOOK> getAllBooks(String shopid) {
        return productDao.getAllBooks(shopid);
    }

    @Override
    public List<T_BOOK> getmoreBooks() {
        return productDao.getmoreBooks();
    }

    @Override
    public List<T_BOOK> getCategoryBooks(char category) {
        return productDao.getCategoryBooks(category);
    }

    @Override
    public T_SHOP getShopByShopid(String shopid) {
        return productDao.getShopByShopid(shopid);
    }

    @Override
    public List<T_SHOP> getGoldShops() {
        return productDao.getGoldShops();
    }

    @Override
    public List<T_BOOK> getrecommendbooks() {
        return productDao.getrecommendbooks();
    }

    @Override
    public List<T_BOOK> getMainbooks() {
        return productDao.getMainbooks();
    }

    @Override
    public List<T_BOOK> allBooks() {
        return productDao.allBooks();
    }

    @Override
    public T_BOOK getBook(String bookId) {
        return productDao.getBook(bookId);
    }

    @Override
    public List<T_BOOK> getSearchBooksByLikeName(String likeName) {
        return productDao.getSearchBooksByLikeName(likeName);
    }
}
