package com.xin.jiushutao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/10 18:42
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class T_BUYER implements Serializable {

    private String t_buyer_id;

    private String t_buyer_name;//买家昵称

    private String t_buyer_phone; //买家电话，同时用于登录用

    private String t_buyer_password; //密码

    private String t_buyer_address; //买家地址

    private String t_order_id; //订单id List<String>

    private String t_cart_id; //购物车id

    private String t_buyer_email;

    private boolean t_buyer_sex; //true为女

    private int t_buyer_login;//1为已登录，0为未登录

    private String t_github_user;//记录从github过来

}
