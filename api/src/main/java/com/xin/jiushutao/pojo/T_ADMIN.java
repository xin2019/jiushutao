package com.xin.jiushutao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/10 18:39
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class T_ADMIN implements Serializable {

    private String t_admin_id; //管理员id

    private String t_admin_name; //管理员名称,多为真实姓名

    private String t_admin_password; //密码

    private String t_admin_phone; //电话号码，同时用于登录是的账号

    private String t_shop_id; //商店id

    private String t_order_id; //订单id

    private String t_seller_id; //卖家id

    private String t_buyer_id; //买家id

}
