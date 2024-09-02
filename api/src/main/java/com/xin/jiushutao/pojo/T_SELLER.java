package com.xin.jiushutao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/10 18:49
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class T_SELLER implements Serializable {

    private String t_seller_id;

    private String t_seller_name; //卖家姓名

    private String t_seller_phone; //卖家电话

    private String t_seller_password; //卖家登录密码

    private String t_shop_id;//卖家的shop id
}
