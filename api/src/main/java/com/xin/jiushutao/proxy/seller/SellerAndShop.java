package com.xin.jiushutao.proxy.seller;

import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_SELLER;
import com.xin.jiushutao.pojo.T_SHOP;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/5 9:31
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerAndShop implements Serializable {
    private T_SELLER t_seller;

    private T_SHOP t_shop;

    private List<T_BOOK> t_books;
}
