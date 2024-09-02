package com.xin.jiushutao.dao;

import com.xin.jiushutao.pojo.T_ADMIN;
import com.xin.jiushutao.pojo.T_BOOK;
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
 * @Date 2021/3/6 20:27
 * @Version 1.0
 **/
@Mapper
@Repository
public interface AdminDao {

    /*
    通过shopid和priority修改shop优先级
     */
    public int updateShopPriorityByShopid(@Param("shopid")String shopid,@Param("priority")String priority);

    /*
    返回所有在主栏位的书籍信息
     */
    public List<T_BOOK> allMainBoooks();

    /*
    返回所有在好书推荐栏位的信息
     */
    public List<T_BOOK> allRecomendBooks();


    /*
    返回所有在金牌书屋栏位的商店信息
     */
    public List<T_SHOP> allGoldShops();

    /*
    提升商铺-》在金牌书屋栏位展示
     */
    public int liftShop(String shopid);

    /*
    提升商铺---》通过商铺id查询商铺持有人信息信息
     */
    @RequestMapping("/getSellerByShopId")

    public T_SELLER getseller(@Param("shopid")String shopid);

    /*
   提升商铺---》通过商铺id查询商铺
    */

    public T_SHOP getshopByShopid(@Param("shopid")String shopid);

    /*
    提升书籍优先级
     */
    public int updatePriorityBook(@Param("priority") String priority, @Param("bookid") String bookid);

    /*
    通过adminphone返回admin对象
     */
    public T_ADMIN getadmin(@Param("phone")String phone);

    /*
    接收来自8002卖家发送的需要提升栏位的书籍id booid
     */
    public int receiveBookid(String bookid);

    /*
    接收来自8002卖家发送的需要提升栏位的商铺id shopid
     */
    public int receiveShopid(String shopid);
}
