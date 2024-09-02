package com.xin.jiushutao.service;

import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_SHOP;
import com.xin.jiushutao.proxy.buyer.BookAndShop;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/18 10:35
 * @Version 1.0
 **/
public interface ProductService {

    /*
          通过shopid返回该商店所有书籍
           */
    @RequestMapping("/getAllBooksByShopid")
    public List<T_BOOK> getAllBooks(String shopid);
    /*
           更多好书栏位
            */
    public List<T_BOOK> getmoreBooks();


    /*
    按书籍分类
     */
    public List<T_BOOK> getCategoryBooks(char category);

    /*
    通过shopid返回shop实体
     */
    @RequestMapping("/getShopByShopid")
    public T_SHOP getShopByShopid(String shopid);
    /*
    获取金牌书屋
     */
    @RequestMapping("/getGoldShops")
    public List<T_SHOP> getGoldShops();


    /*
       获取好书推荐栏位
        */
    @RequestMapping("/getRecomendBooks")
    public List<T_BOOK> getrecommendbooks();

    /*
    获取主栏位书籍
     */
    public List<T_BOOK> getMainbooks();
    /*
   用分页查询，返回所有商品集合
    */
    public List<T_BOOK> allBooks();

    /*
   通过bookid返回该t_book对象

    */
    public T_BOOK getBook(String bookId);


    /*
    返回搜索的书单
     */
    public List<T_BOOK> getSearchBooksByLikeName(String likeName);
}
