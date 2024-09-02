package com.xin.jiushutao.proxy.buyer;

import com.xin.jiushutao.pojo.T_ADDRESS;
import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_BUYER;
import com.xin.jiushutao.pojo.T_ORDER;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description     order+shopname的组合,使用在9001的ordercontroller的方法allOrder中，为了使前台personal——center能获取到商店名称
 * @Date 2021/2/16 16:44
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderShopname implements Serializable {
    private T_ORDER t_order;
    private String shopName;
    private T_BOOK t_book;
    private T_BUYER t_buyer;
    private Double originTotalPrice;
    private T_ADDRESS t_address;
    public OrderShopname(T_ORDER t_order,String shopName,T_BOOK t_book){
        this.t_order=new T_ORDER();
        this.t_order.setT_book_id(t_order.getT_book_id());
//        this.t_order.setT_book_photo(t_order.getT_book_photo());
        this.t_order.setT_bookid_quantity(t_order.getT_bookid_quantity());
        this.t_order.setT_buyer_id(t_order.getT_buyer_id());
        this.t_order.setT_seller_id(t_order.getT_seller_id());
        this.t_order.setT_shop_id(t_order.getT_shop_id());
        this.t_order.setT_order_date(t_order.getT_order_date());
        this.t_order.setT_total_price(t_order.getT_total_price());
        this.t_order.setT_seller_id(t_order.getT_seller_id());
        this.shopName=shopName;
        this.t_book=t_book;
        originTotalPrice=t_order.getT_bookid_quantity()*t_book.getT_book_origin_price();
    }
}
