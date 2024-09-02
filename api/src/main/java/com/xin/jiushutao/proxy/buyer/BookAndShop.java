package com.xin.jiushutao.proxy.buyer;

import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_SHOP;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/8 21:30
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookAndShop implements Serializable {
    private T_BOOK t_book;
    private T_SHOP t_shop;
}
