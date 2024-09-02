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
 * @Date 2021/2/16 0:33
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class T_SHOP implements Serializable {
    private String t_shop_id;

    private String t_shop_name;

    private String t_seller_id;

    private String t_book_id;

    private String t_shop_priority;

    private String t_shop_photo;

    private Date t_priority_begin;//提升商铺的开始时间

    private Date t_priority_end;//结束商铺提升的结束时间
}
