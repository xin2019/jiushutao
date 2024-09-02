package com.xin.jiushutao.dao;

import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_SHOP;
import com.xin.jiushutao.proxy.buyer.BookAndShop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/18 10:25
 * @Version 1.0
 **/
@Mapper
@Repository
public interface ProductDao {

    /*
    获取主栏位书籍
     */
    public List<T_BOOK> getMainbooks();

    /*
    获取好书推荐栏位
     */
    public List<T_BOOK> getrecommendbooks();

    /*
    获取金牌书屋
     */
    public List<T_SHOP> getGoldShops();

    /*
    按书籍分类
     */
    public List<T_BOOK> getCategoryBooks(Character category);


    /*
    通过shopid返回shop实体
     */
    @RequestMapping("/getShopByShopid")
    public T_SHOP getShopByShopid(String shopid);

    /*
     通过shopid返回该商店所有书籍
      */
    @RequestMapping("/getAllBooksByShopid")
    public List<T_BOOK> getAllBooks(@Param("shopid")String shopid);


    /*
        更多好书栏位
         */
    public List<T_BOOK> getmoreBooks();



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
