package com.xin.jiushutao.proxy.buyer;

import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_CART;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/28 20:14
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartAndBook implements Serializable {
    private List<T_BOOK> books;//通过t_cart对象的t_buyer_id获得books书籍列表，其中book
    private String t_cart_id; //等于t_buyer_id的值

    private String t_book_id; //book id

    private int t_book_id_quantity; //购物车的对应该bookid的数量

    private String t_buyer_id; //购物车的买家
}
