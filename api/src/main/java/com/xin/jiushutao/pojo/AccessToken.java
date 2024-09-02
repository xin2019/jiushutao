package com.xin.jiushutao.pojo;

import lombok.Data;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2022/1/23 16:18
 * @Version 1.0
 **/
@Data
public class AccessToken {
    private String client_id;
    private String client_secret;
    private String code;
}
