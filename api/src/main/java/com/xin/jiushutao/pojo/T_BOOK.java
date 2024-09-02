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
 * @Date 2021/2/10 18:35
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class T_BOOK implements Serializable {

    private String t_book_id;

    private String t_book_name; //书名

    private double t_book_origin_price; //原价

    private double t_book_now_price;//现价

    private boolean t_book_is_secondhand; //是否为二手书

    private String t_book_press; //出版社信息

    private String t_shop_id; //外键，商店id

    private char t_book_category; //商店类别

    private String t_book_photo;//商品图片保存路径

    private int t_book_priority; //若为1则在好书推荐一栏展示，为0在更多好书当中展示

    private int t_book_photo_nums; //上传的图片数量

    private String t_book_desc;//商品描述

    private Date t_priority_begin;//提升书籍的开始时间

    private Date t_priority_end;//结束提升书籍的时间

    private int t_book_status;
}
