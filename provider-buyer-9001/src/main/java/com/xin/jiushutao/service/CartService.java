package com.xin.jiushutao.service;

import com.xin.jiushutao.pojo.T_CART;
import com.xin.jiushutao.proxy.buyer.CartAndBook;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/28 21:59
 * @Version 1.0
 **/
public interface CartService {
    /*
 通过t_buyer_id（等于t_cart_id）
  */
    public CartAndBook getBooksByBuyerID(String t_buyer_id);


    /*
    添加商品到购物车
     */
    public boolean addToCart(String bookid,String buyerid,int quantity);


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
