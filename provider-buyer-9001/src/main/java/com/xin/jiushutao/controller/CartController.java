package com.xin.jiushutao.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.xin.jiushutao.pojo.T_CART;
import com.xin.jiushutao.proxy.buyer.CartAndBook;
import com.xin.jiushutao.service.CartService;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/28 21:59
 * @Version 1.0
 **/
@RestController
@RequestMapping("/buyer/cart")
public class CartController {
    @Autowired
    private CartService service;

    /*
 通过t_buyer_id（等于t_cart_id）
  */
    @RequestMapping("/getAllbooks")
    public CartAndBook getBooksByBuyerID(@Param("buyerid") String buyerid){
        return service.getBooksByBuyerID(buyerid);
    }


    /*
    向购物车添加记录
    rt(@Param("bookid") String bookid,@Param("buyerid") String buyerid,@Param("quantity") int quantity);
}
     */
    @RequestMapping("/addToCart")
    public Boolean addToCart(@Param("bookid") String bookid,@Param("buyerid") String buyerid,@Param("quantity") String quantity){
        int nums=Integer.parseInt(quantity);
      return   service.addToCart(bookid,buyerid,nums);
    }


    /*
    通过buyerid返回该买家的所有已加入购物车的商品,
    待改善，可通过直接在数据库用collection查询
     */
    @RequestMapping("/getAllBookIdByBuyerid")
    public List<T_CART> getAllBookIdByBuyerid(@Param("buyerid") String buyerid){
        return service.getAllBookIdByBuyerid(buyerid);
    }

    /*
    通过bookid删除cart中对应的记录
     */
    @RequestMapping("/deleteCartRecordByBookId")
    public Boolean deleteByBookId(@Param("bookid")String bookid){
        return service.deleteCartRecordByBookId(bookid);
    }
}
