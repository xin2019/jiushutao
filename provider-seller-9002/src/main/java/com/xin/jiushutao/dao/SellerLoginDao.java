package com.xin.jiushutao.dao;

import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_ORDER;
import com.xin.jiushutao.pojo.T_SELLER;
import com.xin.jiushutao.pojo.T_SHOP;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/4 19:03
 * @Version 1.0
 **/
@Mapper
@Repository
public interface SellerLoginDao {

    /*
    通过bookid在order表中寻找，返回order对象
     */
    public T_ORDER getOrderByBookid(String bookid);
    /*
       通过bookid和status修改bookid的stauts，确认收货
        */
    public int updateStatusByBookidAndStatus(@Param("bookid")String bookid,@Param("status")int status);

    /*
    查看已被购买书籍
     */
    public List<T_BOOK> getAllBooksPurchase(@Param("sellerid")String sellerid,@Param("status")int status);
    /*
    添加新的卖家
     */
    public boolean addNewSeller(T_SELLER t_seller);

    /*
    卖家登陆
     */
    public T_SELLER isRightSeller(@Param("phone") String phone,@Param("password") String password);

    /*
    根据卖家手机号返回卖家
     */
    public T_SELLER getSeller(@Param("phone")String phone);

    /*
    根据shopid获得shop实体
     */
    public T_SHOP getShop(String shopid);

    /*
    根据shopid获得所有书籍
     */
    public List<T_BOOK> getAllBooksByShopId(String shopid);

    /*
    通过shopid修改商店名称
     */
    public boolean updateShopnameByShopid(@Param("shopid") String shopid,@Param("shopname")String shopname);

    /*
    通过bookid下架book
     */
    public boolean rackBookByBookid(String bookid);


    /*
    新增书籍,在book表和shop表(shop表没新增)
     */
    public boolean addNewBook(T_BOOK t_book);

    /*
    新增默认商店，用于注册新的卖家时候使用
     */
    public boolean addNewShop(T_SHOP t_shop);

}
