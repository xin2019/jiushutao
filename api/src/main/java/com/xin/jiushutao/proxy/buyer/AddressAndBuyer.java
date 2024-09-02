package com.xin.jiushutao.proxy.buyer;

import com.xin.jiushutao.pojo.T_ADDRESS;
import com.xin.jiushutao.pojo.T_BUYER;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/9 8:49
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressAndBuyer implements Serializable {
    private T_BUYER t_buyer;
    private T_ADDRESS t_address;
}
