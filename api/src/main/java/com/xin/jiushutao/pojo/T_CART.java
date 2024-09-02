package com.xin.jiushutao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/10 18:44
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class T_CART implements Serializable {

    private String t_cart_id; //等于t_buyer_id的值

    private String t_book_id; //book id

    private int t_book_id_quantity; //购物车的对应该bookid的数量

    private String t_buyer_id; //购物车的买家
}
