package com.xin.jiushutao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/9 7:48
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class T_ADDRESS implements Serializable {
    private String t_buyer_id;
    private String t_buyer_address;
    private String t_address_id;
    private String t_address_phone;
    private String t_address_name;
    private int t_address_default;
    private int t_address_status;
}
