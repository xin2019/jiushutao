package com.xin.jiushutao.controller;

import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_SHOP;
import com.xin.jiushutao.proxy.buyer.BookAndShop;
import com.xin.jiushutao.service.ProductService;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/18 10:38
 * @Version 1.0
 **/
@RestController
@RequestMapping("/buyer/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/allBooks")
    public List<T_BOOK> allBooks(){
        return productService.allBooks();
    }

    @GetMapping("/getBookByBookId")
    public T_BOOK getBook(@Param("id")String id){
        return productService.getBook(id);
    }


    /*
    返回搜索的书单
     */
    @RequestMapping("/searchBooks")
    @ResponseBody
    public List<T_BOOK> getSearchBooksByLikeName(@Param("likename") String likename){
        return productService.getSearchBooksByLikeName(likename);
    }



    /*
    获取主栏位书籍
     */
    @RequestMapping("/getMainbooks")
    public List<T_BOOK> getMainbooks(){
        return productService.getMainbooks();
    }

    /*
    获取好书推荐栏位
     */
    @RequestMapping("/getRecomendBooks")
    public List<T_BOOK> getrecommendbooks(){
        return productService.getrecommendbooks();
    }


    /*
    获取金牌书屋
     */
    @RequestMapping("/getGoldShops")
    public List<T_SHOP> getGoldShops(){
        return productService.getGoldShops();
    }


    /*
    按书籍分类
     */
    @RequestMapping("getCategoryBooks")
    public List<T_BOOK> getCategoryBooks(@Param("category") char category){
        return productService.getCategoryBooks(category);
    }


    /*
    通过shopid返回shop实体
     */
    @RequestMapping("/getShopByShopid")
    public T_SHOP getShopByShopid(@Param("shopid") String shopid){
        return productService.getShopByShopid(shopid);
    }

    /*
   通过shopid返回该商店所有书籍
    */
    @RequestMapping("/getAllBooksByShopid")
    public List<T_BOOK> getAllBooks(@Param("shopid")String shopid){
        return productService.getAllBooks(shopid);
    }


    /*
       更多好书栏位
        */
    @RequestMapping("/getmoreBooks")
    public List<T_BOOK> getmoreBooks(){
        return productService.getmoreBooks();
    }



}
