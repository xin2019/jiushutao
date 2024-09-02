package com.xin.jiushutao.proxy.buyer;

import com.xin.jiushutao.pojo.T_BUYER;
import com.xin.jiushutao.pojo.T_ORDER;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/21 14:58
 * @Version 1.0
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderAndBuyer extends T_ORDER implements Serializable {
    private T_BUYER t_buyer;
}
