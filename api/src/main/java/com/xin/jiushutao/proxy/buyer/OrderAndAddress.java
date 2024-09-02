package com.xin.jiushutao.proxy.buyer;

import com.xin.jiushutao.pojo.T_ADDRESS;
import com.xin.jiushutao.pojo.T_ORDER;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/21 16:40
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAndAddress implements Serializable {
    private T_ADDRESS t_address;
    private T_ORDER t_order;
}
