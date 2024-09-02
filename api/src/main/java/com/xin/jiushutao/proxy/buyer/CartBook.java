package com.xin.jiushutao.proxy.buyer;

import java.io.Serializable;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/26 19:13
 * @Version 1.0
 **/
public class CartBook implements Serializable {
    private int quantity;//该书本数量
    private String t_book_name;//书本名称
    private String t_shop_id; //属于哪家店
    private String t_shop_name;
    private double t_book_price;
    private String t_cart_id;
    private String t_buyer_id;
    private String t_book_id;
}
