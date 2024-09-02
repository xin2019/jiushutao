package com.xin.jiushutao.dao;

import com.xin.jiushutao.pojo.T_CART;
import com.xin.jiushutao.proxy.buyer.CartAndBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/28 19:10
 * @Version 1.0
 **/
@Repository
@Mapper
public interface CartDao {
    /*
    通过t_buyer_id（等于t_cart_id）
     */
    public CartAndBook getBooksByBuyerID(String t_buyer_id);


    /*
    添加商品到购物车
     */
    public boolean addToCart(@Param("bookid") String bookid,@Param("buyerid") String buyerid,@Param("quantity") int quantity);


    /*
    通过buyerid返回该买家的所有已加入购物车的商品,
    待改善，可通过直接在数据库用collection查询
     */
    public List<T_CART> getAllBookIdByBuyerid(String buyerid);

    /*
    通过bookid删除对应cart记录
     */
    public boolean deleteCartRecordByBookId(String bookid);
}
