package com.xin.jiushutao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/10 18:46
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class T_ORDER implements Serializable {

    private String t_order_id;

    private double t_total_price; //该订单的总共费用

    private String t_buyer_id; //买家信息

    private String t_seller_id; //书籍的卖家信息

    private String t_shop_id; //书籍的商铺信息

    private String t_book_id; //购买的书籍id

    private int t_bookid_quantity; //对应书籍id的购买数量

    private double t_book_now_price;//书本实际单价


    private Date t_order_date;//下单日期
    private int t_book_status;//订单状态
    private String t_address_id;
}
