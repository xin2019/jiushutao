package com.xin.jiushutao.proxy.seller;

import com.xin.jiushutao.pojo.T_ADDRESS;
import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_BUYER;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/11 20:45
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookAndBuyer implements Serializable {
    private T_BOOK t_book;
    private T_BUYER t_buyer;
    private T_ADDRESS t_address;
    private Date date;
    private String t_order_id;
    private String t_order_book_status;
}
