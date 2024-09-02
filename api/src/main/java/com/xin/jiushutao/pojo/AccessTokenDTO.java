package com.xin.jiushutao.pojo;

import lombok.Data;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2022/2/11 16:50
 * @Version 1.0
 **/
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}

