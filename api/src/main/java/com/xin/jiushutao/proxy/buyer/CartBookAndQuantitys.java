package com.xin.jiushutao.proxy.buyer;

import com.xin.jiushutao.pojo.T_BOOK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/3 15:22
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartBookAndQuantitys implements Serializable {
    private T_BOOK t_book;
    private int quantity;
    private double totalPrice;
}
